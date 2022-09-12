package unip.com.domain.model;

import lombok.Data;

@Data
public class SensorData {

    private String[] erros;
    private Integer co2;
    private Integer tvoc;
    private Double temperatura;
    private Integer umidade;

}
