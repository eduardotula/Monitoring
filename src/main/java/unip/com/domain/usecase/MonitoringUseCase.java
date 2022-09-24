package unip.com.domain.usecase;

import unip.com.domain.model.Co2Data;
import unip.com.domain.model.Co2DataRequestEndereco;
import unip.com.domain.model.Esp32;
import unip.com.inbound.port.MonitoringPort;
import unip.com.outbound.adapter.mysql.MonitoringDataAdapter;
import unip.com.outbound.port.Co2DataDataPort;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class MonitoringUseCase implements MonitoringPort {

    @Inject
    Co2DataDataPort co2DataDataPort;
    @Inject
    MonitoringDataAdapter monitoringDataAdapter;

    @Override
    @Transactional
    public Esp32 createEsp32(Esp32 esp32) {
        Esp32 esp32R = monitoringDataAdapter.findEsp32ByIdentificador(esp32.getIdentificador());
        if(Objects.nonNull(esp32R)){
            throw new IllegalArgumentException("Esp32 Já registrado com identificador");
        }

        esp32.generateDefaultConfigParams();
        esp32.setCriadoEm(ZonedDateTime.now());
        return monitoringDataAdapter.save(esp32);
    }


    @Override
    public List<Co2Data> consultarCo2PorEnderecoData(Co2DataRequestEndereco co2DataRequestEndereco) {

        if(Objects.isNull(co2DataRequestEndereco.getCidade())  && Objects.nonNull(co2DataRequestEndereco.getBairro())){
            throw new IllegalArgumentException("Não é possível realizar busca: bairro informado mas cidade não informado");
        }

        List<Co2Data> co2Data = co2DataDataPort.buscarPorEndereco(co2DataRequestEndereco);

        if(Objects.isNull(co2Data) || co2Data.isEmpty()){
            throw new IllegalArgumentException("Nenhum dado encontrado com parametros");
        }

        return co2Data;
    }




}
