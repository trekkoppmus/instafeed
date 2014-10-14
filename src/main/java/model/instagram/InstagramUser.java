package model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties({"bio", "counts"})
public class InstagramUser
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
}
