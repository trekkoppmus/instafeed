package model.instagram;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public class InstagramImage
{
    @JsonProperty
    private int height;
    @JsonProperty
    private int width;
    @JsonProperty
    private URL url;

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public URL getUrl()
    {
        return url;
    }

    public void setUrl(URL url)
    {
        this.url = url;
    }
}
