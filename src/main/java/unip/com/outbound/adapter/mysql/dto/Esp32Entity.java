package unip.com.outbound.adapter.mysql.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;

@Entity
@Table(name = "esp32")
public class Esp32Entity {

    @Column(name = "id")
    private String id;

    @Column(name = "identificador")
    private String identificador;

}
