package unip.com.inbound.adapter.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Co2DataRequestEndereco {

    private String estado;

    private String cidade;

    private String bairro;

    private ZonedDateTime dataInicial;

    private ZonedDateTime dataFinal;
}
