package unip.com.domain.model;


import lombok.*;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Co2Data {

    private Integer id;
    private Esp32 esp32;
    private ZonedDateTime coleta;
    private SensorData sensorData;
    private List<Integer> rawCo2Data = new ArrayList<>();

}
