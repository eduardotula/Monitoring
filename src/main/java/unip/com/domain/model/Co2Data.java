package unip.com.domain.model;

import jdk.jfr.Timestamp;
import lombok.Data;

@Data
public class Co2Data {

    private Timestamp epoch;
    private Integer identifier;
    private SensorData sensor;

}
