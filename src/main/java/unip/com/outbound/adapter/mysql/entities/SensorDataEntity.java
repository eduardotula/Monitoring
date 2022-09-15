package unip.com.outbound.adapter.mysql.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "sensor_data")
@Table(name = "sensor_data")
public class SensorDataEntity {

    @Id
    @Column(name = "id", columnDefinition = "char", length = 36)
    private String id;

    @OneToOne(mappedBy = "sensorData")
    private Co2DataEntity co2Data;

    @Column(name = "erros")
    private String erros;

    @Column(name = "co2", columnDefinition = "integer")
    private Integer co2;

    @Column(name = "tvoc", columnDefinition = "integer")
    private Integer tvoc;

    @Column(name = "temperatura", columnDefinition = "decimal(5,2)")
    private Double temperatura;

    @Column(name = "umidade", columnDefinition = "integer")
    private Integer umidade;


}
