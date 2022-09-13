package unip.com.inbound.adapter.mappers;

import org.mapstruct.Mapper;
import unip.com.domain.model.Co2Data;
import unip.com.inbound.adapter.dto.Co2DataDto;

@Mapper(componentModel = "cdi")
public interface Co2DataDtoMapper {

    Co2DataDto co2Data2Co2DataDto(Co2Data co2Data);
    Co2Data co2DataDto2Co2Data(Co2DataDto co2DataDto);


}
