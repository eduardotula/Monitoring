package unip.com.inbound.port;

import unip.com.domain.model.Esp32;

public interface MonitoringPort {

    Esp32 createEsp32(Esp32 esp32);

}
