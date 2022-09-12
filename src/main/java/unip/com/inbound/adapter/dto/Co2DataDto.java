package unip.com.inbound.adapter.dto;

import jdk.jfr.Timestamp;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class Co2DataDto {

    @NotNull(message = "epoch não encontrado!")
    private Instant epoch;

    @NotNull(message = "identifier não encontrado!")
    private Integer identifier;

    @NotNull(message = "sensorData não encontrado!")
    @Valid
    private SensorDataDto sensorDataDto;

}
