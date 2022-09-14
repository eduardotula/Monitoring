package unip.com.outbound.adapter.mysql.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "co2_data")
public class Co2DataEntity {

    @Column(name = "id", columnDefinition = "char", length = 36)
    @Id
    private String id;

    @Column(name = "id_esp", columnDefinition = "char", length = 36)
    private String idEsp32;

    @Column(name = "coleta")
    private ZonedDateTime coleta;

    @Column(name = "erros")
    private String erros;

    @Column(name = "co2", columnDefinition = "integer")
    private Integer co2;

    @Column(name = "tvoc", columnDefinition = "integer")
    private Integer tvoc;

    @Column(name = "co2", columnDefinition = "decimal(18,2)")
    private Double temperatura;

    @Column(name = "co2", columnDefinition = "integer")
    private Integer umidade;

}
