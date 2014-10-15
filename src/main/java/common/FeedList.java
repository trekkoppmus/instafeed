package common;


import model.common.CommonItem;

import javax.ejb.Singleton;
import java.util.*;

@Singleton
public class FeedList
{
    private List<CommonItem> feedList = new LinkedList<>();

    public void addItems(Collection<CommonItem> collection)
    {
        for (CommonItem item : collection)
        {
            System.out.println(feedList.contains(item));

            if(!feedList.contains(item)) {
                feedList.add(item);
            }
        }
    }

    public List<CommonItem> getFeedList() {
        return feedList;
    }
}
