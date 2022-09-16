package unip.com.outbound.port;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.SensorData;

public interface Co2DataDataPort {

    Co2Data saveCo2Data(Co2Data co2DataEntity);
    SensorData saveSensorData(SensorData sensorData);


}
