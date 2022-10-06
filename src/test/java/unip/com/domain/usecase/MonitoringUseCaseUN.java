package unip.com.domain.usecase;

import com.workday.insights.timeseries.arima.struct.ArimaParams;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Co2DataRequestEndereco;
import unip.com.domain.model.Esp32;
import unip.com.domain.usecase.MonitoringUseCase;
import unip.com.domain.usecase.factories.Co2DataFactory;
import unip.com.domain.usecase.factories.Esp32Factory;
import unip.com.inbound.adapter.dto.ArimaForecastResponse;
import unip.com.outbound.port.Co2DataDataPort;
import unip.com.outbound.port.MonitoringDataPort;

import javax.inject.Inject;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class MonitoringUseCaseUN {

    @InjectMock
    MonitoringDataPort monitoringDataPort;
    @InjectMock
    Co2DataDataPort co2DataDataPort;
    @Inject
    MonitoringUseCase monitoringUseCase;
    @Inject
    Esp32Factory esp32Factory;
    @Inject
    Co2DataFactory co2DataFactory;

    @Test
    public void createEsp32() {
        var esp32 = esp32Factory.createEsp32(null);

        when(monitoringDataPort.findEsp32ByIdentificador(esp32.getIdentificador())).thenReturn(null);
        when(monitoringDataPort.save(esp32)).thenReturn(esp32);

        var esp32Registered = monitoringUseCase.createEsp32(esp32);

        assertNotNull(esp32Registered);
        assertNotNull(esp32Registered.getConfigParams());
        assertFalse(esp32Registered.getConfigParams().isEmpty());
        assertNotNull(esp32Registered.getCriadoEm());
    }

    @Test
    void updateEsp32() {
        var esp32 = esp32Factory.createEsp32(null);
        esp32.generateDefaultConfigParams();
        var exitentExp32 = esp32Factory.createEsp32(10);


        when(monitoringDataPort.findEsp32ByIdentificador(esp32.getIdentificador())).thenReturn(exitentExp32);
        when(monitoringDataPort.save(esp32)).thenReturn(exitentExp32);

        var esp32Registered = monitoringUseCase.updateEsp32(esp32);

        assertNotNull(esp32Registered);
        assertNotNull(esp32Registered.getId());
    }

    @Test
    void consultarCo2PorEnderecoData() {
        var co2DataRequestEndereco = Co2DataRequestEndereco.builder().estado("estado").dataFinal(ZonedDateTime.now()).dataInicial(ZonedDateTime.now())
                .bairro("bairro").cidade("cidade").build();

        var co21 = co2DataFactory.createCo2(2);
        var co22 = co2DataFactory.createCo2(4);
        co22.getSensorData().setCo2(560);
        var co23 = co2DataFactory.createCo2(3);
        co23.getSensorData().setCo2(800);
        var listCo2 = new ArrayList<Co2Data>();
        listCo2.addAll(Arrays.asList(co21, co22, co23));

        when(co2DataDataPort.buscarPorEndereco(co2DataRequestEndereco)).thenReturn(listCo2);

        var listCo2R = monitoringUseCase.consultarCo2PorEnderecoData(co2DataRequestEndereco);
        assertTrue( listCo2R.size() == 3);
        assertTrue(listCo2R.containsAll(Arrays.asList(co21, co22, co23)));
    }


    @Test
    void consultarEsp32sParaProximaManutencao() {
        List<Esp32> esp32List = new ArrayList<>();
        Esp32 esp32 = esp32Factory.createEsp32(10);
        esp32List.add(esp32);

        when(monitoringDataPort.listarEsp32sParaProximaManutencao(any())).thenReturn(esp32List);

        List<Esp32> listEsp32R = monitoringUseCase.consultarEsp32sParaProximaManutencao();
        assertTrue(listEsp32R.contains(esp32));

    }

    @Test
    void timeSeriesForecast() {
        ArimaParams arimaParams = new ArimaParams(1,1,1,1,1,1,1);
        int tamanhoPred = 10;
        double[] data = new double[]{1,2,3,4,5,6,7,8,9,10};

        ArimaForecastResponse re = monitoringUseCase.timeSeriesForecast(arimaParams,tamanhoPred, data);
        var average = Arrays.stream(re.getForecastResult()).average();
        assertEquals(15.5,average.getAsDouble());
    }
}