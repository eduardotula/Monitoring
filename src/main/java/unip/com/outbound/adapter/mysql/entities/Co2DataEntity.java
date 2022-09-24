package unip.com.outbound.adapter.mysql.entities;


import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "co2Data")
@Table(name = "co2_data")
public class Co2DataEntity {

    @Column(name = "id", columnDefinition = "INTEGER")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_esp32", referencedColumnName = "id", foreignKey = @ForeignKey(name = "co2_data_esp32_fk"))
    private Esp32Entity esp32;

    @Column(name = "coleta")
    private ZonedDateTime coleta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sensor_data", referencedColumnName = "id", foreignKey = @ForeignKey(name = "co2_data_sensor_data_fk"))
    private SensorDataEntity sensorData;

}
