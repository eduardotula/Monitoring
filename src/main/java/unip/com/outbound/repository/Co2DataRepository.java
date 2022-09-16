package unip.com.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unip.com.outbound.adapter.mysql.entities.Co2DataEntity;

public interface Co2DataRepository extends JpaRepository<Co2DataEntity, Integer> {


}
