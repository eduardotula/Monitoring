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

    private Integer id;
    private String identificador;
    private String nomeRua;
    private String numero;
    private String cidade;
    private String bairro;
    private String cep;
    private String estado;
    private String pais;
    private String latitude;
    private String longitude;
    private Integer altura;
    private ZonedDateTime criadoEm;

    private List<Co2Data> co2Data;
}
