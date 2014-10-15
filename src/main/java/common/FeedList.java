package common;


import model.common.CommonItem;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FeedList
{
    private static FeedList me;
    private List<CommonItem> feedList = new LinkedList<>();

    public void addItems(Collection<CommonItem> collection)
    {
        for (CommonItem item : collection)
        {
            if(!feedList.contains(item)) {
                feedList.add(item);
            }
        }
    }

    public List<CommonItem> getFeedList() {
        return feedList;
    }

    public static FeedList getInstance()
    {
        if(me == null) me = new FeedList();
        return me;
    }
}
