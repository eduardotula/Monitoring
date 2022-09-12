package unip.com.outbound.mapper;

import org.mapstruct.Mapper;
import unip.com.domain.model.Co2Data;
import unip.com.outbound.adapter.mysql.dto.Co2DataEntity;

@Mapper(componentModel = "cdi")
public interface Co2DataEntityMapper {

    Co2DataEntity co2Data2Co2DataEntity(Co2Data co2Data);
    Co2Data co2DataEntity2Co2Data(Co2DataEntity co2DataEntity);
}
