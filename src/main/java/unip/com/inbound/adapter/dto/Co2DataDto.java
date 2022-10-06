package unip.com.inbound.adapter.dto;

import jdk.jfr.Timestamp;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Co2DataDto {

    private Integer id;

    @NotNull(message = "epoch não encontrado!")
    private Integer epoch;

    @NotNull(message = "identifier não encontrado!")
    private Integer identificador;

    @NotNull(message = "sensorData não encontrado!")
    @Valid
    private SensorDataDto sensorData;

    private List<Integer> rawCo2Data;

}
