package unip.com.inbound.adapter.mappers;

import org.mapstruct.Mapper;
import unip.com.domain.model.Co2DataRequestEndereco;
import unip.com.inbound.adapter.dto.consulta.Co2DataRequestEnderecoDto;

@Mapper(componentModel = "cdi")
public interface Co2DataRequestEnderecoDtoMapper {

    Co2DataRequestEnderecoDto toDto(Co2DataRequestEndereco co2DataRequestEndereco);
    Co2DataRequestEndereco toModel(Co2DataRequestEnderecoDto dto);

}
