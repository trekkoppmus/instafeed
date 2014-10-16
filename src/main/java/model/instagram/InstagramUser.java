package model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties({"bio", "counts"})
public class InstagramUser implements Comparable<InstagramUser>
{
    @JsonProperty("username")
    private String userName;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("profile_picture")
    private URL profilePicture;

    @JsonProperty
    private long id;

    @JsonProperty
    private URL website;

    public URL getWebsite()
    {
        return website;
    }

    public void setWebsite(URL website)
    {
        this.website = website;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public URL getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(URL profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public int compareTo(InstagramUser o)
    {
        int webVal = 0;
        if (website == null && o.getWebsite() == null)
        {
            webVal = 0;
        } else if (website == null)
        {
            webVal = 1;
        } else if (o.getWebsite() == null)
        {
            webVal = -1;
        } else
        {
            webVal = website.toString().compareTo(o.getWebsite().toString());
        }

        return userName.compareTo(o.getUserName())
                + fullName.compareTo(o.getFullName())
                + profilePicture.toString().compareTo(o.getProfilePicture().toString())
                + webVal
                + (int) (id - o.getId());
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof InstagramUser)) return false;
        return compareTo((InstagramUser) o) == 0;
    }
}
