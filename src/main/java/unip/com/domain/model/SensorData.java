package unip.com.domain.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorData {

    private Integer id;
    private String erros;
    private Integer co2;
    private Integer tvoc;
    private Double temperatura;
    private Integer umidade;

}
