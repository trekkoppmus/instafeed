package model.instagram;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstagramLocation implements Comparable<InstagramLocation>
{
    @JsonProperty
    private double latitude;
    @JsonProperty
    private double longitude;
    @JsonProperty
    private long id;
    @JsonProperty
    private String name;

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int compareTo(InstagramLocation o)
    {
        return (int)(latitude - o.getLatitude()) + (int)(longitude - o.getLongitude()) + (int)(id - o.id) + name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof InstagramLocation)) return false;
        return compareTo((InstagramLocation)o) == 0;
    }
}
