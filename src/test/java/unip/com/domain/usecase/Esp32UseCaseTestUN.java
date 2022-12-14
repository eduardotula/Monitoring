package unip.com.domain.usecase;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import unip.com.domain.model.Esp32ConfigParams;
import unip.com.domain.usecase.factories.Co2DataFactory;
import unip.com.domain.usecase.factories.Esp32Factory;
import unip.com.outbound.port.Co2DataDataPort;
import unip.com.outbound.port.MonitoringDataPort;

import javax.inject.Inject;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class Esp32UseCaseTestUN {

    @InjectMock
    MonitoringDataPort monitoringDataPort;
    @Inject
    Esp32UseCase esp32UseCase;
    @InjectMock
    Co2DataDataPort co2DataDataPort;
    @Inject
    Co2DataFactory co2DataFactory;
    @Inject
    Esp32Factory esp32Factory;

    @Test
    void now() {
        var now = esp32UseCase.now();
        Instant instant = Instant.ofEpochSecond(Long.parseLong(now));
    }

    @Test
    void saveCo2Data() {
        var co2 = co2DataFactory.createCo2(null);
        co2.getEsp32().setIdentificador("2646");
        var co2R = co2DataFactory.createCo2(1);
        var esp32 = esp32Factory.createEsp32(2);
        esp32.setIdentificador("2646");

        when(monitoringDataPort.findEsp32ByIdentificador(anyString())).thenReturn(esp32);
        when(co2DataDataPort.saveCo2Data(co2)).thenReturn(co2R);

        var co2Response = esp32UseCase.saveCo2Data(co2);
        assertNotNull(co2Response.getId());
    }

    @Test
    void getConfigParamsActive() {
        Esp32ConfigParams esp32ConfigParams = Esp32ConfigParams.builder().id(1).param("teste").value("valor").active(true).build();
        when(monitoringDataPort.findEsp32WithConfigActive(anyString())).thenReturn(Arrays.asList(esp32ConfigParams));
        when(monitoringDataPort.findEsp32ByIdentificador(anyString())).thenReturn(esp32Factory.createEsp32(2));

        List<Esp32ConfigParams> list = esp32UseCase.getConfigParamsActive("iden");
        assertTrue(list.contains(esp32ConfigParams));
    }
}