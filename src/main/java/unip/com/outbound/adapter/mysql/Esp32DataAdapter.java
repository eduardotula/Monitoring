package unip.com.outbound.adapter.mysql;

import unip.com.domain.model.Co2Data;
import unip.com.outbound.adapter.mysql.entities.Co2DataEntity;
import unip.com.outbound.mapper.Co2DataEntityMapper;
import unip.com.outbound.port.Esp32DataPort;
import unip.com.outbound.repository.Co2DataRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Esp32DataAdapter implements Esp32DataPort {

    @Inject
    Co2DataRepository co2DataRepository;
    @Inject
    Co2DataEntityMapper co2DataEntityMapper;

    @Override
    public Co2Data saveEsp32Data(Co2Data co2Data) {
        Co2DataEntity co2DataEntity = co2DataEntityMapper.toEntity(co2Data);
        return co2DataEntityMapper.toModel(co2DataRepository.save(co2DataEntity));
    }

    @Override
    public Co2Data findById(String id) {
        return co2DataEntityMapper.toModel(co2DataRepository.findById(id).get());
    }
}
