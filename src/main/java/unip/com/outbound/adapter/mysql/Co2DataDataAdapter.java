package unip.com.outbound.adapter.mysql;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Co2DataRequestEndereco;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public List<Co2Data> buscarPorEndereco(Co2DataRequestEndereco co2DataRequestEndereco) {
        Stream<Co2DataEntity> list;

        if(Objects.nonNull(co2DataRequestEndereco.getCidade())){
            list = co2DataRepository.findByEsp32Cidade(co2DataRequestEndereco.getPais(), co2DataRequestEndereco.getCidade(), co2DataRequestEndereco.getDataInicial(),
                    co2DataRequestEndereco.getDataFinal());

        } else if (Objects.nonNull(co2DataRequestEndereco.getBairro())) {
            list = co2DataRepository.findByEsp32CidadeBairro(co2DataRequestEndereco.getPais(),
                    co2DataRequestEndereco.getCidade(), co2DataRequestEndereco.getBairro(),
                    co2DataRequestEndereco.getDataInicial(), co2DataRequestEndereco.getDataFinal());

        }else{
            list = co2DataRepository.findByEsp32Pais(co2DataRequestEndereco.getPais(), co2DataRequestEndereco.getDataInicial(),
                    co2DataRequestEndereco.getDataFinal());
        }

        return list.map(co2DataEntityMapper::toModel).collect(Collectors.toList());
    }


}
