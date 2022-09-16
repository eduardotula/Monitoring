package unip.com.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unip.com.outbound.adapter.mysql.entities.SensorDataEntity;

public interface SensorDataRepository extends JpaRepository<SensorDataEntity, Integer> {
}
