package model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.instagram.InstagramImage;
import model.instagram.InstagramUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@JsonIgnoreProperties({"type", "filter", "caption", "link", "location"})
public class CommonItem implements Comparable<CommonItem>
{
    @JsonProperty
    private InstagramUser user;
    @JsonProperty
    private List<String> tags;

    private Date createdTime;
    @JsonProperty
    private HashMap<String, InstagramImage> images;

    @JsonProperty
    private String id;


    public InstagramUser getUser()
    {
        return user;
    }

    public void setUser(InstagramUser user)
    {
        this.user = user;
    }

    public List<String> getTags()
    {
        return tags;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    @JsonProperty("created_time")
    public void setCreatedTime(Date creationTime)
    {
        this.createdTime = creationTime;
    }

    public HashMap<String, InstagramImage> getImages()
    {
        return images;
    }

    public void setImages(HashMap<String, InstagramImage> images)
    {
        this.images = images;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public int compareTo(CommonItem o)
    {
        return user.compareTo(o.getUser()) + createdTime.compareTo(o.getCreatedTime()) + id.compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof CommonItem)) return false;
        return this.compareTo((CommonItem)o) == 0;
    }
}
