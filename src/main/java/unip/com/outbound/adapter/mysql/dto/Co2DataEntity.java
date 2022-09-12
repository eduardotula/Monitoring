package unip.com.outbound.adapter.mysql.dto;


import javax.persistence.Entity;
import java.time.Instant;
import java.util.List;

//TODO terminar essa classe com anotações de tabela
@Entity
public class Co2DataEntity {

    private String id;
    private String idEsp32;
    private Instant epoch;

    private List<String> erros;
    private Integer co2;
    private Integer tvoc;
    private Double temperatura;
    private Integer umidade;

}
