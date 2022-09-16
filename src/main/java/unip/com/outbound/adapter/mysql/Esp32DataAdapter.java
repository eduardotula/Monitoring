package unip.com.outbound.adapter.mysql;

import unip.com.domain.model.Esp32;
import unip.com.inbound.port.MonitoringPort;
import unip.com.outbound.adapter.mysql.entities.Esp32Entity;
import unip.com.outbound.mapper.Esp32EntityMapper;
import unip.com.outbound.port.MonitoringDataPort;
import unip.com.outbound.repository.Esp32Repository;

import javax.inject.Inject;
import java.util.Objects;

public class Esp32DataAdapter implements MonitoringDataPort {

    @Inject
    Esp32Repository esp32Repository;
    @Inject
    Esp32EntityMapper esp32EntityMapper;

    @Override
    public Esp32 createEsp32(Esp32 esp32) {
        Esp32Entity esp32Entity = esp32EntityMapper.toEntity(esp32);
        return esp32EntityMapper.toModel(esp32Repository.save(esp32Entity));
    }

    @Override
    public Esp32 findEsp32ById(String id) {
        Esp32Entity esp32Entity = esp32Repository.findById(id).get();
        if(Objects.isNull(esp32Entity)) return null;
        return esp32EntityMapper.toModel(esp32Entity);
    }

    @Override
    public Esp32 findEsp32ByIdentificador(String identificador) {
        Esp32Entity esp32Entity = esp32Repository.findByIdentificador(identificador);
        if(Objects.isNull(esp32Entity)) return null;
        return esp32EntityMapper.toModel(esp32Entity);
    }
}
