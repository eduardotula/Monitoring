package unip.com.outbound.adapter.mysql.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sensorData")
@Table(name = "sensor_data")
public class SensorDataEntity {

    @Id
    @Column(name = "id", columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
