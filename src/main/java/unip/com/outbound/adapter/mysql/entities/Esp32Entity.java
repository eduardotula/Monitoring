package unip.com.outbound.adapter.mysql.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
@Data
@Entity(name = "esp32")
@Table(name = "esp32")
public class Esp32Entity {

    @Id
    @Column(name = "id", columnDefinition = "char", length = 36)
    private String id;

    //TODO colocar char e lengh do identificador
    @Column(name = "identificador")
    private String identificador;

    @Column(name = "nome_rua")
    private String nomeRua;

    @Column(name = "numero")
    private String numero;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @Column(name = "latitude", columnDefinition = "decimal(6,8)")
    private BigDecimal latitude;

    @Column(name = "longitude", columnDefinition = "decimal(6,8)")
    private BigDecimal longitude;

    @Column(name = "altura")
    private Integer altura;

    @Column(name = "criado_em")
    private ZonedDateTime criadoEm;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "esp32")
    private List<Co2DataEntity> co2Data;

}
