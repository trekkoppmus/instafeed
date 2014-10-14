package instagram;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.instagram.InstagramData;
import model.instagram.InstagramJSON;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.net.URL;

@Startup
@Singleton
public class Timer {

    @Schedule(hour = "*", minute = "*")
    void updateFeed()
    {
        try
        {
            URL url = new URL("https://api.instagram.com/v1/tags/javaBin/media/recent?client_id=f05c3a9ab28f465bb9cb2c5d7c002dce");
            ObjectMapper mapper = new ObjectMapper();
            InstagramJSON root = mapper.readValue(url, InstagramJSON.class);

            for(InstagramData d: root.getData()) {
                System.out.println(d.toString());
            }
        } catch (Exception e)
        {
            System.err.println(e.toString());
        }

    }
}
