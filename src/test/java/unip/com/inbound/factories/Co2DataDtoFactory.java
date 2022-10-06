package unip.com.inbound.factories;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Esp32;
import unip.com.domain.model.SensorData;
import unip.com.inbound.adapter.dto.Co2DataDto;
import unip.com.inbound.adapter.dto.SensorDataDto;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZonedDateTime;
import java.util.Objects;

@ApplicationScoped
public class Co2DataDtoFactory {


    public Co2DataDto createCo2(Integer identificador){
        SensorDataDto sensorData = createsensorData();
        return Co2DataDto.builder().identificador(identificador).epoch((int) ZonedDateTime.now().toEpochSecond()).sensorData(sensorData).build();
    }

    public SensorDataDto createsensorData(){
        return SensorDataDto.builder().co2(456).tvoc(2).temperatura(25.6).umidade(30).build();
    }
}
