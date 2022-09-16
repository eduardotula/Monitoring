package unip.com.outbound.adapter.mysql;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Esp32;
import unip.com.domain.model.SensorData;
import unip.com.outbound.adapter.mysql.entities.Co2DataEntity;
import unip.com.outbound.adapter.mysql.entities.Esp32Entity;
import unip.com.outbound.adapter.mysql.entities.SensorDataEntity;
import unip.com.outbound.mapper.Co2DataEntityMapper;
import unip.com.outbound.mapper.Esp32EntityMapper;
import unip.com.outbound.mapper.SensorDataEntityMapper;
import unip.com.outbound.port.Co2DataDataPort;
import unip.com.outbound.repository.Co2DataRepository;
import unip.com.outbound.repository.Esp32Repository;
import unip.com.outbound.repository.SensorDataRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class Co2DataDataAdapter implements Co2DataDataPort {

    @Inject
    Co2DataRepository co2DataRepository;
    @Inject
    SensorDataRepository sensorDataRepository;
    @Inject
    SensorDataEntityMapper sensorDataEntityMapper;
    @Inject
    Co2DataEntityMapper co2DataEntityMapper;
    @Inject
    Esp32Repository esp32Repository;
    @Inject
    Esp32EntityMapper esp32EntityMapper;

    @Override
    public Co2Data saveCo2Data(Co2Data co2Data) {
        Co2DataEntity co2DataEntity = co2DataEntityMapper.toEntity(co2Data);
        return co2DataEntityMapper.toModel(co2DataRepository.save(co2DataEntity));
    }

    @Override
    public SensorData saveSensorData(SensorData sensorData) {
        SensorDataEntity sensorDataEntity = sensorDataEntityMapper.toEntity(sensorData);
        return sensorDataEntityMapper.toModel(sensorDataRepository.save(sensorDataEntity));
    }


}
