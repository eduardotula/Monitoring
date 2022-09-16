package unip.com.outbound.port;

import unip.com.domain.model.Esp32;

public interface MonitoringDataPort {

    Esp32 createEsp32(Esp32 esp32);

    Esp32 findEsp32ById(String id);

    Esp32 findEsp32ByIdentificador(String id);

}
