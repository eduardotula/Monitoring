package unip.com.inbound.adapter;

import unip.com.domain.model.Esp32;
import unip.com.inbound.adapter.dto.Esp32Dto;
import unip.com.inbound.adapter.dto.consulta.Co2DataRequestEnderecoDto;
import unip.com.inbound.adapter.mappers.Esp32DtoMapper;
import unip.com.inbound.port.MonitoringPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/monitoring")
public class MonitoringRestAdapter {

    @Inject
    Esp32DtoMapper esp32DtoMapper;
    @Inject
    MonitoringPort monitoringPort;

    @Path("/esp32")
    @POST
    public Esp32Dto createEsp32(@Valid Esp32Dto esp32Dto){
        Esp32 esp32 = esp32DtoMapper.toModel(esp32Dto);
        return esp32DtoMapper.toDto(monitoringPort.createEsp32(esp32));
    }

    @Path("/co2data")
    @POST
    public Esp32Dto consultarCo2PorEnderecoData(Co2DataRequestEnderecoDto co2DataRequestEnderecoDto){
        Esp32 esp32 = esp32DtoMapper.toModel(co2DataRequestEnderecoDto);
        return esp32DtoMapper.toDto(monitoringPort.createEsp32(esp32));
    }


}
