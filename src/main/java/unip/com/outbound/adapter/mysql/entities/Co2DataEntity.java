package unip.com.outbound.adapter.mysql.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity(name = "co2_data")
@Table(name = "co2_data")
public class Co2DataEntity {

    @Column(name = "id", columnDefinition = "char", length = 36)
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_esp32")
    private Esp32Entity esp32;

    @Column(name = "coleta")
    private ZonedDateTime coleta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sensorData")
    private SensorDataEntity sensorData;

}
