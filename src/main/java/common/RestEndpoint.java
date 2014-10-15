package common;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
public class RestEndpoint
{
    @Inject
    FeedList feedList;

    @GET
    @Produces("application/json")
    public Response get() {
        return Response.ok(feedList.getFeedList()).build();
    }
}
