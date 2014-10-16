package instagram;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.FeedList;
import common.MyProperties;
import model.common.CommonItem;
import model.instagram.InstagramData;
import model.instagram.InstagramJSON;
import model.instagram.InstagramMediaType;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Startup
@Singleton
public class Timer {

    MyProperties properties = MyProperties.getInstance();

    FeedList feedList = FeedList.getInstance();

    @PostConstruct
    @Schedule(hour = "*", minute = "*")
    void updateItems()
    {
        List<InstagramData> list = null;
        for(String tag: properties.getTags())
        {
            List<InstagramData> tmp = getItems(tag, properties.getClientId(), properties.getNumImages());

            if(list == null)
            {
                list = tmp;
            }

            if(list == null || tmp == null) continue;
            list.retainAll(tmp);
        }


        ObjectMapper mapper = new ObjectMapper();
        Collection<CommonItem> collection = new ArrayList<>();
        for(InstagramData data: list) {
            if(data.getType() == InstagramMediaType.image)
            {
                CommonItem item = mapper.convertValue(data, CommonItem.class);
                collection.add(item);
            }
        }

        feedList.addItems(collection);
    }

    private List<InstagramData> getItems(String tag, String client_id, String numImages)
    {
        if(tag == null || client_id == null) {
            return null;
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
            return null;
        }
    }
}
