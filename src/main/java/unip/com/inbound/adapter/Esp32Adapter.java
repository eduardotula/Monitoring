package unip.com.inbound.adapter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Path("esp32")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Esp32Adapter {

    @Path("now")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String now(){
        long epochSeconds = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        return Long.toString(epochSeconds);
    }

}
