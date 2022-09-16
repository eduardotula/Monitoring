package unip.com.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZonedDateTime;

@Data
public class Co2Data {

    private Integer id;
    private Esp32 esp32;
    private ZonedDateTime coleta;
    private SensorData sensorData;

}
