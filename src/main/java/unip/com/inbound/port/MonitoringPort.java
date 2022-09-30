package unip.com.inbound.port;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Co2DataRequestEndereco;
import unip.com.domain.model.Esp32;

import java.util.List;

public interface MonitoringPort {

    Esp32 createEsp32(Esp32 esp32);
    Esp32 updateEsp32(Esp32 esp32);
    List<Co2Data> consultarCo2PorEnderecoData(Co2DataRequestEndereco co2DataRequestEndereco, boolean raw);
    List<Esp32> consultarEsp32sParaProximaManutencao();

    void timeSeriesForecast();
}
