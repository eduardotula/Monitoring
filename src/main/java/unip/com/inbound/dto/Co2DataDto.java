package unip.com.inbound.dto;

import jdk.jfr.Timestamp;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class Co2DataDto {

    @NotNull(message = "epoch não encontrado!")
    private Timestamp epoch;

    @NotNull(message = "identifier não encontrado!")
    private Integer identifier;

    @NotNull(message = "sensorData não encontrado!")
    @Valid
    private SensorDataDto sensorDataDto;

}
