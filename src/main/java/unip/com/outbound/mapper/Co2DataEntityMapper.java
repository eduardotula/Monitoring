package unip.com.outbound.mapper;

import org.mapstruct.Mapper;
import unip.com.domain.model.Co2Data;
import unip.com.outbound.adapter.mysql.entities.Co2DataEntity;

@Mapper(componentModel = "cdi")
public interface Co2DataEntityMapper {

    Co2DataEntity toEntity(Co2Data co2Data);
    Co2Data toModel(Co2DataEntity co2DataEntity);
}
