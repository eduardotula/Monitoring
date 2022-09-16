package unip.com.domain.usecase;

import unip.com.domain.model.Esp32;
import unip.com.inbound.adapter.Esp32RestAdapter;
import unip.com.inbound.port.MonitoringPort;
import unip.com.outbound.adapter.mysql.Esp32DataAdapter;
import unip.com.outbound.port.Co2DataDataPort;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;

@RequestScoped
public class MonitoringUseCase implements MonitoringPort {

    @Inject
    Co2DataDataPort co2DataDataPort;
    @Inject
    Esp32DataAdapter esp32DataAdapter;

    @Inject
    @Override
    public Esp32 createEsp32(Esp32 esp32) {

        Esp32 esp32R = esp32DataAdapter.findEsp32ByIdentificador(esp32.getIdentificador());
        if(Objects.nonNull(esp32R)){
            throw new IllegalArgumentException("Esp32 Já registrado com identificador");
        }
        return esp32DataAdapter.createEsp32(esp32);
    }
}
