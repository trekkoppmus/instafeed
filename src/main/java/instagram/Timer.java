package instagram;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habosa.javasnap.Snap;
import com.habosa.javasnap.Snapchat;
import common.FeedList;
import common.MyProperties;
import model.common.CommonItem;
import model.instagram.InstagramData;
import model.instagram.InstagramJSON;
import model.instagram.InstagramMediaType;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Startup
@Singleton
public class Timer
{

    MyProperties properties = MyProperties.getInstance();

    FeedList feedList = FeedList.getInstance();

    @PreDestroy
    void save()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String fileName = properties.get("filename");
            mapper.writeValue(new File(fileName), feedList.getFeedList());
        } catch (IOException e)
        {
            System.err.println(e.toString());
        }
    }

    @PostConstruct
    void load() {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String fileName = properties.get("filename");
            feedList.addItems((Collection<CommonItem>)mapper.readValue(new File(fileName),
                    mapper.getTypeFactory().constructCollectionType(List.class, CommonItem.class)));
        } catch (IOException e)
        {
            System.err.println(e.toString());
        }
    }

    @Schedule(hour = "*", minute = "*")
    void updateItems()
    {
        List<InstagramData> list = new ArrayList<>();
        for (String tag : properties.getTags())
        {
            System.out.println(tag);
            List<InstagramData> tmp = getItems(tag, properties.getClientId(), properties.getNumImages());

            if (list == null || tmp == null) continue;

            if(list.size() == 0) {
                list.addAll(tmp);
            } else
            {
                list.retainAll(tmp);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        Collection<CommonItem> collection = new ArrayList<>();
        for (InstagramData data : list)
        {
            if (data.getType() == InstagramMediaType.image)
            {
                CommonItem item = mapper.convertValue(data, CommonItem.class);
                collection.add(item);
            }
        }

        feedList.addItems(collection);
        save();
    }

    //@Schedule(hour = "*", minute = "*")
    private void getSnaps() {
        try
        {
            String password = properties.get("snapPass");
            String username = properties.get("snapUser");

            Snapchat snapchat = Snapchat.login(username, password);
            if (snapchat.refresh())
            {
                for (Snap snap : snapchat.getSnaps())
                {
                    if (!snap.isDownloadable()) continue;

                    if (snap.isMedia() && snap.isImage())
                    {
                        byte[] snapBytes = snapchat.getSnap(snap);
                        File snapFile = new File(String.format(
                                "%s/%s.jpg",
                                properties.get("snapPath"),
                                "test"
                        ));

                        FileOutputStream snapOS = new FileOutputStream(snapFile);
                        snapOS.write(snapBytes);
                        snapOS.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<InstagramData> getItems(String tag, String client_id, String numImages)
    {
        if (tag == null || client_id == null)
        {
            return new ArrayList<InstagramData>();
        }

        try
        {
            URL url = new URL(String.format("https://api.instagram.com/v1/tags/%s/media/recent?client_id=%s&count=%s",
                    tag, client_id, numImages));
            ObjectMapper mapper = new ObjectMapper();
            InstagramJSON root = mapper.readValue(url, InstagramJSON.class);

            return root.getData();
        } catch (Exception e)
        {
            System.err.println(e.toString());
            return new ArrayList<>();
        }
    }
}
