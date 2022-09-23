package unip.com.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unip.com.outbound.adapter.mysql.entities.Co2DataEntity;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

@ApplicationScoped
public interface Co2DataRepository extends JpaRepository<Co2DataEntity, Integer> {

    @Query("SELECT c FROM co2Data c WHERE c.esp32.pais = :pais AND c.esp32.cidade = :cidade AND c.coleta BETWEEN :dataInicial AND :dataFinal")
    Stream<Co2DataEntity> findByEsp32(String pais, String cidade, ZonedDateTime dataInicial, ZonedDateTime dataFinal);
//    Stream<Co2DataEntity> findByEsp32(String pais, String cidade, String municipio, LocalDate dataInicial, LocalDate dataFinal);
//    Stream<Co2DataEntity> findByEsp32(String pais, String cidade, String municipio, String bairro, LocalDate dataInicial, LocalDate dataFinal);


}
