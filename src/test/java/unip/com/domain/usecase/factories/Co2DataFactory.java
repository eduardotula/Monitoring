package unip.com.domain.usecase.factories;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Esp32;
import unip.com.domain.model.SensorData;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZonedDateTime;
import java.util.Objects;

@ApplicationScoped
public class Co2DataFactory {

    public Co2Data createCo2(Integer id){
        SensorData sensorData = createsensorData(Objects.nonNull(id)? id++ : null);
        return Co2Data.builder().id(id).coleta(ZonedDateTime.now()).esp32(Esp32.builder().id(25).build()).sensorData(sensorData).build();
    }

    public SensorData createsensorData(Integer id){
        return SensorData.builder().id(id).co2(456).tvoc(2).temperatura(25.6).umidade(30).build();
    }
}
