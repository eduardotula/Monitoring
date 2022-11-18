package unip.com.control;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;

import unip.com.control.exceptions.RequestException;
import unip.com.model.ArimaForecastRequest;
import unip.com.model.ArimaForecastResponse;
import unip.com.model.Co2DataDto;

public class MonitoringPort {

	private Requests request;

	public MonitoringPort() {
		this.request = new Requests("https://monitoring-esp32.herokuapp.com");
	}

	@SuppressWarnings("unchecked")
	public List<Co2DataDto> listCo2ByDateAndFullAddress(LocalDateTime dataInicial, LocalDateTime dataFinal,
			String estado, String cidade, String bairro) throws RequestException {
		HashMap<String, String> params = new HashMap<String, String>();
		
		if(Objects.nonNull(dataInicial)) params.put("data_inicial", dataInicial.toString());
		if(Objects.nonNull(dataFinal))params.put("data_final", dataFinal.toString());
		if(Objects.nonNull(estado) && !estado.isEmpty())params.put("estado", estado);
		if(Objects.nonNull(cidade) && !cidade.isEmpty())params.put("cidade", cidade);
		if(Objects.nonNull(bairro) && !bairro.isEmpty())params.put("bairro", bairro);

		return (List<Co2DataDto>) request.getRequest(new TypeReference<List<Co2DataDto>>() {}, params, "/monitoring/co2data");

	}
	
	public ArimaForecastResponse getArimaPrediction(ArimaForecastRequest arimaParams)throws RequestException {
		
		List<ArimaForecastResponse> re = (List<ArimaForecastResponse>) request.postRequest(new TypeReference<ArimaForecastResponse>() {}, null, "/monitoring/co2data/arima", arimaParams);
		if(!re.isEmpty()) return re.get(0);
		return null;
	}
}
