package unip.com.inbound.adapter.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unip.com.domain.model.Co2Data;
import unip.com.inbound.adapter.dto.Co2DataDto;

@Mapper(componentModel = "cdi")
public interface Co2DataDtoMapper {

    @Mapping(source = "esp32.identificador", target = "identificador")
    Co2DataDto co2Data2Co2DataDto(Co2Data co2Data);
    @Mapping(source = "identificador", target = "esp32.identificador")
    Co2Data co2DataDto2Co2Data(Co2DataDto co2DataDto);


}
