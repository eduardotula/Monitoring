package unip.com.inbound.adapter;

import unip.com.domain.model.Co2DataRequestEndereco;
import unip.com.domain.model.Esp32;
import unip.com.domain.model.Esp32ConfigParams;
import unip.com.inbound.adapter.dto.Co2DataDto;
import unip.com.inbound.adapter.dto.Esp32Dto;
import unip.com.inbound.adapter.mappers.Co2DataDtoMapper;
import unip.com.inbound.adapter.mappers.Esp32DtoMapper;
import unip.com.inbound.port.MonitoringPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/monitoring")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitoringRestAdapter {

    @Inject
    Esp32DtoMapper esp32DtoMapper;
    @Inject
    MonitoringPort monitoringPort;
    @Inject
    Co2DataDtoMapper co2DataDtoMapper;

    @Path("/esp32")
    @POST
    public Esp32Dto createEsp32(@Valid Esp32Dto esp32Dto){
        Esp32 esp32 = esp32DtoMapper.toModel(esp32Dto);
        return esp32DtoMapper.toDto(monitoringPort.createEsp32(esp32));
    }

    @Path("/esp32")
    @PUT
    public Esp32Dto updateEsp32(@Valid Esp32Dto esp32Dto){
        Esp32 esp32 = esp32DtoMapper.toModel(esp32Dto);
        return esp32DtoMapper.toDto(monitoringPort.updateEsp32(esp32));
    }

    @Path("/co2data")
    @GET
    public List<Co2DataDto> consultarCo2PorEnderecoData(@QueryParam("pais")@NotNull String pais,
                                                        @QueryParam("cidade") String cidade,
                                                        @QueryParam("bairro") String bairro,
                                                        @QueryParam("data_inicial") @NotNull String dataInicial,
                                                        @QueryParam("data_final")@NotNull String dataFinal,
                                                        @QueryParam("raw") boolean raw){
        ZonedDateTime dataIni;
        ZonedDateTime dataFin;
        try{
            dataFin = ZonedDateTime.of(LocalDateTime.parse(dataFinal), ZoneId.systemDefault());
            dataIni = ZonedDateTime.of(LocalDateTime.parse(dataInicial), ZoneId.systemDefault());
        }catch (Exception e){
            throw new IllegalArgumentException("Data inicial ou data final não esta em padrão valido");
        }
        Co2DataRequestEndereco co2DataRequestEndereco = new Co2DataRequestEndereco(
                pais, cidade, bairro, dataIni, dataFin);
        return monitoringPort.consultarCo2PorEnderecoData(co2DataRequestEndereco, raw).stream().map(co2DataDtoMapper::co2Data2Co2DataDto)
                .collect(Collectors.toList());
    }

    @Path("/co2data/raw")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String consultarCo2PorEnderecoDataRawText(@QueryParam("pais")@NotNull String pais,
                                                        @QueryParam("cidade") String cidade,
                                                        @QueryParam("bairro") String bairro,
                                                        @QueryParam("data_inicial") @NotNull String dataInicial,
                                                        @QueryParam("data_final")@NotNull String dataFinal){
        ZonedDateTime dataIni;
        ZonedDateTime dataFin;
        try{
            dataFin = ZonedDateTime.of(LocalDateTime.parse(dataFinal), ZoneId.systemDefault());
            dataIni = ZonedDateTime.of(LocalDateTime.parse(dataInicial), ZoneId.systemDefault());
        }catch (Exception e){
            throw new IllegalArgumentException("Data inicial ou data final não esta em padrão valido");
        }
        Co2DataRequestEndereco co2DataRequestEndereco = new Co2DataRequestEndereco(
                pais, cidade, bairro, dataIni, dataFin);
        StringBuilder builder = new StringBuilder();
        monitoringPort.consultarCo2PorEnderecoData(co2DataRequestEndereco, false).stream().forEach(co2Data ->  {
            String data = String.format("%d,%s\n", co2Data.getSensorData().getCo2(), co2Data.getColeta().toLocalDateTime().toString());
            builder.append(data);
        });
        return builder.toString();
    }

    @Path("/esp32/consulta_proximaManutencao")
    @GET
    public List<Esp32Dto> consultarEsp32sParaProximaManutencao(){
        return monitoringPort.consultarEsp32sParaProximaManutencao().stream().map(esp32DtoMapper::toDto).collect(Collectors.toList());
    }


}
