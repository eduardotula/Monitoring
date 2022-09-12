package unip.com.inbound.port;

import unip.com.domain.model.Co2Data;

public interface Esp32Port {

    String now();
    Co2Data saveCo2Data(Co2Data co2);

}
