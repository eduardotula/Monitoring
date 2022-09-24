package unip.com.outbound.port;

import unip.com.domain.model.Esp32;
import unip.com.domain.model.Esp32ConfigParams;

import java.util.List;

public interface MonitoringDataPort {

    Esp32 save(Esp32 esp32);

    Esp32 findEsp32ById(Integer id);

    Esp32 findEsp32ByIdentificador(String id);
    List<Esp32ConfigParams> findEsp32WithConfigActive(String identificador);

}
