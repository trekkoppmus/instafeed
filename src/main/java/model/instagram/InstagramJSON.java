package model.instagram;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

public class InstagramJSON
{
    @JsonProperty
    private Map<String, String> pagination;
    @JsonProperty
    private Map<String, String> meta;
    @JsonProperty
    private ArrayList<InstagramData> data;

    public Map<String, String> getPagination()
    {
        return pagination;
    }

    public void setPagination(Map<String, String> pagination)
    {
        this.pagination = pagination;
    }

    public Map<String, String> getMeta()
    {
        return meta;
    }

    public void setMeta(Map<String, String> meta)
    {
        this.meta = meta;
    }

    public ArrayList<InstagramData> getData()
    {
        return data;
    }

    public void setData(ArrayList<InstagramData> data)
    {
        this.data = data;
    }
}
