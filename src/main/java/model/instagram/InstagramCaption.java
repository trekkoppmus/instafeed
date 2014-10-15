package model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties({"from"})
public class InstagramCaption implements Comparable<InstagramCaption>
{
    @JsonProperty("created_time")
    private Date createdTime;
    @JsonProperty
    private String text;
    @JsonProperty
    private Long id;

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int compareTo(InstagramCaption o)
    {
        return id.compareTo(o.id) + text.compareTo(o.text) + createdTime.compareTo(o.createdTime);
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof InstagramCaption)) return false;
        return compareTo((InstagramCaption)o) == 0;
    }
}
