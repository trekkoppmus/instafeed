package common;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class MyProperties
{
    private static MyProperties me = null;

    private String clientId, clientSecret;
    private List<String> tags = new ArrayList<>();
    private String numImages;

    public MyProperties()
    {
        try
        {
            Properties properties = new Properties();
            InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");

            properties.load(inputStream);

            setClientId(properties.getProperty("clientId"));
            setClientSecret(properties.getProperty("clientSecret"));
            setNumImages(properties.getProperty("imageCount", "1000"));

            StringTokenizer tokenizer = new StringTokenizer(properties.getProperty("tags"), ",");
            List<String> list = new ArrayList<>();

            while (tokenizer.hasMoreTokens())
            {
                list.add(tokenizer.nextToken());
            }

            setTags(list);


        } catch (Exception e) {
            System.err.println("Hmm: " + e.toString());
        }
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getClientSecret()
    {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;
    }

    public List<String> getTags()
    {
        return tags;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    public static MyProperties getInstance()
    {
        if(me == null) me = new MyProperties();
        return me;
    }

    public void setNumImages(String numImages)
    {
        this.numImages = numImages;
    }

    public String getNumImages()
    {
        return numImages;
    }
}
