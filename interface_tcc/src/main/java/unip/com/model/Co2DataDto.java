package unip.com.model;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;


@Data
public class Co2DataDto {

    private Integer id;

    private Integer epoch;

    private Integer identificador;

    private SensorDataDto sensorData;

    private List<Integer> rawCo2Data;

    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEpoch() {
		return epoch;
	}

	public void setEpoch(Integer epoch) {
		this.epoch = epoch;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public SensorDataDto getSensorData() {
		return sensorData;
	}

	public void setSensorData(SensorDataDto sensorData) {
		this.sensorData = sensorData;
	}

	public List<Integer> getRawCo2Data() {
		return rawCo2Data;
	}

	public void setRawCo2Data(List<Integer> rawCo2Data) {
		this.rawCo2Data = rawCo2Data;
	}

    
    
}
