package unip.com.inbound.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SensorDataDto {

    @NotNull(message = "erros não encontrado!")
    private String[] erros;

    @NotNull(message = "co2 não encontrado!")
    private Integer co2;

    @NotNull(message = "tvoc não encontrado!")
    private Integer tvoc;

    @NotNull(message = "temperatura não encontrada!")
    private Double temperatura;

    @NotNull(message = "temperatura não encontrada!")
    private Integer umidade;
}
