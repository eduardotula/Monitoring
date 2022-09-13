package unip.com.domain.usecase;

import unip.com.domain.model.Co2Data;
import unip.com.inbound.port.Esp32Port;
import unip.com.outbound.port.Esp32DataPort;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Esp32UseCase implements Esp32Port {

    @Inject
    Esp32DataPort esp32DataPort;

    @Override
    public String now() {
        long epochSeconds = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        return Long.toString(epochSeconds);
    }

    @Override
    public Co2Data saveCo2Data(Co2Data co2) {
        return null;
    }


}
