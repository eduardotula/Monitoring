package unip.com.inbound.adapter.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unip.com.domain.model.Co2Data;
import unip.com.inbound.adapter.dto.Co2DataDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Mapper(componentModel = "cdi")
public interface Co2DataDtoMapper {

    @Mapping(source = "esp32.identificador", target = "identificador")
    @Mapping(source = "coleta", target = "epoch")
    Co2DataDto co2Data2Co2DataDto(Co2Data co2Data);
    @Mapping(source = "identificador", target = "esp32.identificador")
    @Mapping(source = "epoch", target = "coleta")
    Co2Data co2DataDto2Co2Data(Co2DataDto co2DataDto);

    default ZonedDateTime toZoned(Integer epoch){
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(epoch), ZoneId.systemDefault());
    }

    default Integer toEpoch(ZonedDateTime coleta){
        return Math.toIntExact(coleta.toEpochSecond());
    }

}
