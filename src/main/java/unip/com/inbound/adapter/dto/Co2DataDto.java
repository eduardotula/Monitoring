package unip.com.inbound.adapter.dto;

import jdk.jfr.Timestamp;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.ZonedDateTime;

@Data
public class Co2DataDto {

    private Integer id;

    @NotNull(message = "epoch não encontrado!")
    private Integer epoch;

    @NotNull(message = "identifier não encontrado!")
    private Integer identificador;

    @NotNull(message = "sensorData não encontrado!")
    @Valid
    private SensorDataDto sensorData;

}
