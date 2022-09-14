package unip.com.outbound.port;

import unip.com.domain.model.Co2Data;
import unip.com.outbound.adapter.mysql.dto.Co2DataEntity;

public interface Esp32DataPort {

    Co2Data saveEsp32Data(Co2Data co2DataEntity);

    Co2Data findById(String id);
}
