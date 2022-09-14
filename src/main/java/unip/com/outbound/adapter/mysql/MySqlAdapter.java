package unip.com.outbound.adapter.mysql;

import unip.com.domain.model.Co2Data;
import unip.com.outbound.adapter.mysql.dto.Co2DataEntity;
import unip.com.outbound.mapper.Co2DataEntityMapper;
import unip.com.outbound.port.Esp32DataPort;
import unip.com.outbound.repository.Co2DataRepository;

import javax.inject.Inject;

public class MySqlAdapter implements Esp32DataPort {

    @Inject
    Co2DataRepository co2DataRepository;
    @Inject
    Co2DataEntityMapper co2DataEntityMapper;

    @Override
    public Co2Data saveEsp32Data(Co2Data co2Data) {
        Co2DataEntity co2DataEntity = co2DataEntityMapper.co2Data2Co2DataEntity(co2Data);
        return co2DataEntityMapper.co2DataEntity2Co2Data(co2DataRepository.save(co2DataEntity));
    }

    @Override
    public Co2Data findById(String id) {
        return co2DataEntityMapper.co2DataEntity2Co2Data(co2DataRepository.findById(id).get());
    }
}
