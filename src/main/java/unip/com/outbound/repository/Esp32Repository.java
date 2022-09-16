package unip.com.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unip.com.outbound.adapter.mysql.entities.Esp32Entity;

public interface Esp32Repository extends JpaRepository<Esp32Entity, String> {

    Esp32Entity findByIdentificador(String identificador);
}
