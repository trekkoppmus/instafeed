package instagram;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Timer {

    @Schedule(hour = "*", minute = "*")
    void updateFeed()
    {
        System.out.println("Time!");
    }
}
