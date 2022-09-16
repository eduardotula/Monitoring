package unip.com.inbound.adapter;

import unip.com.domain.model.Co2Data;
import unip.com.inbound.adapter.dto.Co2DataDto;
import unip.com.inbound.adapter.mappers.Co2DataDtoMapper;
import unip.com.inbound.port.Esp32Port;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("esp32/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Esp32RestAdapter {

    @Inject
    Esp32Port esp32Port;

    @Inject
    Co2DataDtoMapper co2DataDtoMapper;

    @Path("now/")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String now(){
        return esp32Port.now();
    }

    @Path("data/")
    @POST
    public Co2DataDto createCo2Data(Co2DataDto co2DataDto) {
        Co2Data co2Data = co2DataDtoMapper.co2DataDto2Co2Data(co2DataDto);
        Co2DataDto co2DataDto1 = co2DataDtoMapper.co2Data2Co2DataDto(esp32Port.saveCo2Data(co2Data, co2DataDto.getEpoch()));
        return co2DataDto1;
    }

}
