package unip.com.inbound.port;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Esp32ConfigParams;

import java.util.List;

public interface Esp32Port {

    String now();

    Co2Data saveCo2Data(Co2Data co2);

    List<Esp32ConfigParams> getConfigParamsActive(String identificador);

}
