package unip.com.inbound.adapter;

import unip.com.inbound.port.Esp32Port;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("esp32/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Esp32Adapter {

    @Inject
    Esp32Port esp32Port;

    @Path("now/")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String now(){
        return esp32Port.now();
    }

}
