package unip.com.domain.usecase;

import com.workday.insights.timeseries.arima.Arima;
import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Co2DataRequestEndereco;
import unip.com.domain.model.Esp32;
import unip.com.inbound.port.MonitoringPort;
import unip.com.outbound.port.Co2DataDataPort;
import unip.com.outbound.port.MonitoringDataPort;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class MonitoringUseCase implements MonitoringPort {

    @Inject
    Co2DataDataPort co2DataDataPort;
    @Inject
    MonitoringDataPort monitoringDataPort;

    @Override
    @Transactional
    public Esp32 createEsp32(Esp32 esp32) {
        Esp32 esp32R = monitoringDataPort.findEsp32ByIdentificador(esp32.getIdentificador());
        if(Objects.nonNull(esp32R)){
            throw new IllegalArgumentException("Esp32 Já registrado com identificador");
        }

        esp32.generateDefaultConfigParams();
        esp32.setCriadoEm(ZonedDateTime.now());
        return monitoringDataPort.save(esp32);
    }

    @Override
    public Esp32 updateEsp32(Esp32 esp32) {
        Esp32 esp32R = monitoringDataPort.findEsp32ByIdentificador(esp32.getIdentificador());

        if(Objects.isNull(esp32)){
            throw new IllegalArgumentException("Esp32 não encontrado com identificador");
        }
        esp32.setId(esp32R.getId());
        esp32.setCriadoEm(esp32R.getCriadoEm());
        esp32.getConfigParams().forEach(c -> c.setActive(true));
        return monitoringDataPort.save(esp32);
    }

    @Override
    public List<Co2Data> consultarCo2PorEnderecoData(Co2DataRequestEndereco co2DataRequestEndereco, boolean raw) {

        if(Objects.isNull(co2DataRequestEndereco.getCidade())  && Objects.nonNull(co2DataRequestEndereco.getBairro())){
            throw new IllegalArgumentException("Não é possível realizar busca: bairro informado mas cidade não informado");
        }

        List<Co2Data> co2Data = co2DataDataPort.buscarPorEndereco(co2DataRequestEndereco);

        if(Objects.isNull(co2Data) || co2Data.isEmpty()){
            throw new IllegalArgumentException("Nenhum dado encontrado com parametros");
        }

        if(raw){
            Co2Data rawCo2Data = new Co2Data();
            co2Data.stream().forEach(co2Data1 -> rawCo2Data.getRawCo2Data().add(co2Data1.getSensorData().getCo2()));
            return Arrays.asList(rawCo2Data);
        }

        return co2Data;
    }

    @Override
    public List<Esp32> consultarEsp32sParaProximaManutencao(){
        var dataPrevista = LocalDate.now();
        List<Esp32> esp32s = monitoringDataPort.listarEsp32sParaProximaManutencao(dataPrevista);

        if(Objects.isNull(esp32s) || esp32s.isEmpty()){
            throw new IllegalArgumentException("Nenhum dado encontrado com parametros");
        }
        return esp32s;
    }

    @Override
    public void timeSeriesForecast(){
    }

}
