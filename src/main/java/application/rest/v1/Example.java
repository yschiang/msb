package application.rest.v1;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;

import javax.inject.Inject;
import application.bluemix.ServiceName;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.model.storage.object.SwiftContainer;

@Path("v1/example")
public class Example {

    @Inject
    @ServiceName(name="demo2-Object-Storage-5395")
    protected OSClientV3 os;



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("objectstorage")
    public Response exampleObjectStorage() {
        try {
          SwiftAccount account = os.objectStorage().account().get();
          List<? extends SwiftContainer> containers = os.objectStorage().containers().list();
          return Response.ok("Account: " + account + " Containers: " + containers).build();
        } catch (NullPointerException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
