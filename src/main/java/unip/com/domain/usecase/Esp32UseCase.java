package unip.com.domain.usecase;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Esp32;
import unip.com.domain.model.Esp32ConfigParams;
import unip.com.inbound.port.Esp32Port;
import unip.com.outbound.adapter.mysql.MonitoringDataAdapter;
import unip.com.outbound.port.Co2DataDataPort;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class Esp32UseCase implements Esp32Port {

    @Inject
    Co2DataDataPort co2DataDataPort;
    @Inject
    MonitoringDataAdapter monitoringDataAdapter;

    @Override
    public String now() {
        long epochSeconds = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        return Long.toString(epochSeconds);
    }

    @Override
    @Transactional
    public Co2Data saveCo2Data(Co2Data co2) {
        Esp32 esp32 = monitoringDataAdapter.findEsp32ByIdentificador(co2.getEsp32().getIdentificador());
        if(Objects.isNull(esp32)){
            throw new IllegalArgumentException("Esp32 não cadastrado");
        }

        co2.setSensorData(co2DataDataPort.saveSensorData(co2.getSensorData()));
        co2.setEsp32(esp32);
        return co2DataDataPort.saveCo2Data(co2);
    }

    @Override
    public List<Esp32ConfigParams> getConfigParamsActive(String identificador) {
        var config = monitoringDataAdapter.findEsp32WithConfigActive(identificador);

        if(Objects.isNull(config) || config.isEmpty()){
            throw new IllegalArgumentException("Nenhuma configuração ativa encontrada");
        }

        Esp32 esp32 = monitoringDataAdapter.findEsp32ByIdentificador(identificador);
        if(Objects.isNull(esp32)) throw new IllegalArgumentException("Esp32 não encontrado");

        esp32.getConfigParams().forEach(e -> e.setActive(false));
        monitoringDataAdapter.save(esp32);

        return config;
    }


}
