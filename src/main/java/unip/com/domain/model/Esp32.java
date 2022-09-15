package unip.com.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Esp32 {

    private String id;
    private String identificador;
    private String nomeRua;
    private String numero;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;
    private String pais;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer altura;
    private ZonedDateTime criadoEm;

    private List<Co2Data> co2Data;
}
