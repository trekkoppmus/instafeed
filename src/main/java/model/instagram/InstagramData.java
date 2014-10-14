package model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@JsonIgnoreProperties({"users_in_photo", "comments", "likes", "attribution"})
public class InstagramData
{
    @JsonProperty
    private InstagramMediaType type;
    @JsonProperty
    private String filter;
    @JsonProperty
    private Set<String> tags;
    @JsonProperty
    private InstagramCaption caption;
    @JsonProperty
    private URL link;
    @JsonProperty
    private InstagramUser user;
    @JsonProperty("created_time")
    private long createdTime;
    @JsonProperty
    private Map<String, InstagramImage> images;
    @JsonProperty
    private String id;
    @JsonProperty
    private InstagramLocation location;

    public String getFilter()
    {
        return filter;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    public InstagramUser getUser()
    {
        return user;
    }

    public void setUser(InstagramUser user)
    {
        this.user = user;
    }

    public Date getCreatedTime()
    {
        return new Date(createdTime * 1000);
    }

    public void setCreatedTime(long createdTime)
    {
        this.createdTime = createdTime;
    }

    public Map<String, InstagramImage> getImages()
    {
        return images;
    }

    public void setImages(Map<String, InstagramImage> images)
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

    public InstagramLocation getLocation()
    {
        return location;
    }

    public void setLocation(InstagramLocation location)
    {
        this.location = location;
    }

    public URL getLink()
    {
        return link;
    }

    public void setLink(URL link)
    {
        this.link = link;
    }

    public InstagramCaption getCaption()
    {
        return caption;
    }

    public void setCaption(InstagramCaption caption)
    {
        this.caption = caption;
    }

    public InstagramMediaType getType()
    {
        return type;
    }

    public void setType(InstagramMediaType type)
    {
        this.type = type;
    }

    public Set<String> getTags()
    {
        return tags;
    }

    public void setTags(Set<String> tags)
    {
        this.tags = tags;
    }

    @Override
    public String toString()
    {
        return getCreatedTime().toString();
    }
}
