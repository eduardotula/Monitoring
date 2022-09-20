package unip.com.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Co2DataRequestEndereco {

    private String pais;

    private String cidade;

    private String municipio;

    private String bairro;

    private LocalDate dataInicial;

    private LocalDate dataFinal;
}
