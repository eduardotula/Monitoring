package unip.com.outbound.adapter.mysql.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import org.jboss.resteasy.spi.touri.MappedBy;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
@Data
@Entity(name = "esp32")
@Table(name = "esp32")
public class Esp32Entity implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "altura")
    private Integer altura;

    @Column(name = "criado_em")
    private ZonedDateTime criadoEm;

}
