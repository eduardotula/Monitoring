package unip.com.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Co2DataRequestEndereco {

    private String pais;

    private String cidade;

    private String bairro;

    private ZonedDateTime dataInicial;

    private ZonedDateTime dataFinal;
}
