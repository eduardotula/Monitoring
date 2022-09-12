package unip.com.domain.usecase;

import unip.com.domain.model.Co2Data;
import unip.com.inbound.port.Esp32Port;

public class Esp32UseCase implements Esp32Port {
    @Override
    public String now() {
        return null;
    }

    @Override
    public Co2Data saveCo2Data(Co2Data co2Data) {
        return null;
    }
}
