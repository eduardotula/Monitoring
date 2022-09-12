package unip.com.domain.model;

import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.Instant;

@Data
public class Co2Data {

    private Instant epoch;
    private Integer identifier;
    private SensorData sensor;

}
