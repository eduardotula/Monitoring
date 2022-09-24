package unip.com.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unip.com.outbound.adapter.mysql.entities.Esp32Entity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

public interface Esp32Repository extends JpaRepository<Esp32Entity, Integer> {

    Esp32Entity findByIdentificador(String identificador);
}
