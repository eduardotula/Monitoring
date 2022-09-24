package unip.com.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Esp32ConfigParams> configParams;

    public void generateDefaultConfigParams(){
        if(Objects.isNull(configParams)) configParams = new ArrayList<>();
        configParams.add(Esp32ConfigParams.builder().param("ccs.mode").value("1").active(true).build());
        configParams.add(Esp32ConfigParams.builder().param("ccs.reset").value("1").active(true).build());
        configParams.add(Esp32ConfigParams.builder().param("delayReciveLog").value("5000").active(true).build());
        configParams.add(Esp32ConfigParams.builder().param("delaySendData").value("10000").active(true).build());
    }

}
