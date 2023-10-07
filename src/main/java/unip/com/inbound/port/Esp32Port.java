package unip.com.inbound.port;

import unip.com.domain.model.Data;
import unip.com.domain.model.Esp32ConfigParams;

import java.util.List;

public interface Esp32Port {

    String now();

    Data saveCo2Data(Data co2);

    List<Esp32ConfigParams> getConfigParamsActive(String identificador);

}
