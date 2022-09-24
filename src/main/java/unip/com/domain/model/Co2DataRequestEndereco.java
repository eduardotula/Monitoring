package unip.com.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Co2DataRequestEndereco {

    private String pais;

    private String cidade;

    private String bairro;

    private ZonedDateTime dataInicial;

    private ZonedDateTime dataFinal;
}
