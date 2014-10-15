package instagram;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/instagram")
public class RestEndpoint {

    @GET
    public Response getList ()
    {
        return Response.ok().build();
    }
}
