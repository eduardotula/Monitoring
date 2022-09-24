package unip.com.inbound.adapter;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Esp32ConfigParams;
import unip.com.inbound.adapter.dto.Co2DataDto;
import unip.com.inbound.adapter.dto.Esp32ConfigParamsDto;
import unip.com.inbound.adapter.mappers.Co2DataDtoMapper;
import unip.com.inbound.adapter.mappers.Esp32ConfigparamsDtoMapper;
import unip.com.inbound.port.Esp32Port;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("esp32/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Esp32RestAdapter {

    @Inject
    Esp32Port esp32Port;

    @Inject
    Co2DataDtoMapper co2DataDtoMapper;
    @Inject
    Esp32ConfigparamsDtoMapper esp32ConfigparamsDtoMapper;

    @Path("now/")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String now(){
        return esp32Port.now();
    }

    @Path("data/")
    @POST
    public Co2DataDto createCo2Data(@Valid Co2DataDto co2DataDto) {
        Co2Data co2Data = co2DataDtoMapper.co2DataDto2Co2Data(co2DataDto);
        Co2DataDto co2DataDto1 = co2DataDtoMapper.co2Data2Co2DataDto(esp32Port.saveCo2Data(co2Data));
        return co2DataDto1;
    }

    @Path("configParams/")
    @GET
    public List<Esp32ConfigParamsDto> getConfigParamsActive(@QueryParam("identificador") @NotNull String identificador){
        return esp32Port.getConfigParamsActive(identificador).stream().map(esp32ConfigparamsDtoMapper::toDto).collect(Collectors.toList());
    }
}
