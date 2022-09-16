package unip.com.domain.usecase;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Esp32;
import unip.com.inbound.port.Esp32Port;
import unip.com.outbound.adapter.mysql.Esp32DataAdapter;
import unip.com.outbound.port.Co2DataDataPort;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@RequestScoped
public class Esp32UseCase implements Esp32Port {

    @Inject
    Co2DataDataPort esp32DataPort;
    @Inject
    Esp32DataAdapter esp32DataAdapter;

    @Override
    public String now() {
        long epochSeconds = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        return Long.toString(epochSeconds);
    }

    @Override
    public Co2Data saveCo2Data(Co2Data co2) {
        Esp32 esp32 = esp32DataAdapter.findEsp32ByIdentificador(co2.getEsp32().getIdentificador());
        if(Objects.isNull(esp32)){
            throw new IllegalArgumentException("Esp32 não cadastrado");
        }

        return esp32DataPort.saveCo2Data(co2);
    }


}
