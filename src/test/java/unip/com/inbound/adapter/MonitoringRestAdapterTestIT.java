package unip.com.inbound.adapter;

import com.workday.insights.timeseries.arima.struct.ArimaParams;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unip.com.domain.model.Co2Data;
import unip.com.domain.usecase.factories.Co2DataFactory;
import unip.com.domain.usecase.factories.Esp32Factory;
import unip.com.inbound.adapter.dto.ArimaForecastRequest;
import unip.com.inbound.adapter.dto.ArimaForecastResponse;
import unip.com.inbound.adapter.dto.Co2DataDto;
import unip.com.inbound.adapter.dto.Esp32Dto;
import unip.com.inbound.factories.Co2DataDtoFactory;
import unip.com.inbound.factories.Esp32DtoFactory;
import unip.com.outbound.port.Co2DataDataPort;
import unip.com.outbound.port.MonitoringDataPort;
import unip.com.outbound.repository.Co2DataRepository;
import unip.com.outbound.repository.Esp32ConfigRepository;
import unip.com.outbound.repository.Esp32Repository;
import unip.com.outbound.repository.SensorDataRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MonitoringRestAdapterTestIT {

    @Inject
    Esp32DtoFactory esp32Factory;
    @Inject
    Co2DataDtoFactory co2DataFactory;
    @Inject
    Esp32Repository esp32Repository;
    @Inject
    Co2DataRepository co2DataRepository;
    @Inject
    Esp32ConfigRepository esp32ConfigRepository;
    @Inject
    SensorDataRepository sensorDataRepository;

    @BeforeEach
    @Transactional
    public void before(){
        esp32ConfigRepository.deleteAll();
        co2DataRepository.deleteAll();
        sensorDataRepository.deleteAll();
        esp32Repository.deleteAll();
    }

    public Esp32Dto createEsp32(){
        var esp = esp32Factory.createEsp32(null);
        esp.setProximaManutencao(esp.getProximaManutencao().minusDays(100));
        return given().when().contentType(MediaType.APPLICATION_JSON)
                .body(esp)
                .post("/monitoring/esp32")
                .then().extract().as(Esp32Dto.class);
    }

    public Co2DataDto createCo2Data(Integer identificador){
        var co2 = co2DataFactory.createCo2(identificador);
        return given().when().contentType(MediaType.APPLICATION_JSON)
                .body(co2)
                .post("/esp32/data/")
                .then().extract().as(Co2DataDto.class);
    }

    @Test
    @TestTransaction
    void createEsp32EntaoSucesso() {
        Esp32Dto esp32Dto = esp32Factory.createEsp32(null);

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(esp32Dto)
                .post("/monitoring/esp32")
                .then();

        System.out.println(response.extract().asString());

        Esp32Dto esp32DtoResponse = response.extract().as(Esp32Dto.class);

        assertFalse(esp32DtoResponse.getConfigParams().isEmpty());
        assertNotNull(esp32DtoResponse.getId());

    }
    @Test
    void createEsp32WithIdenficadorJaRegistradoFalha() {
        createEsp32();
        Esp32Dto esp32Dto = esp32Factory.createEsp32(null);

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(esp32Dto)
                .post("/monitoring/esp32")
                .then();

        System.out.println(response.extract().asString());
        response.body("Requisição_inválida.mensagem", equalTo("Esp32 Já registrado com identificador"));

    }

    @Test
    void updateEsp32EntaoSucesso() {
        var firstEsp32 = createEsp32();
        firstEsp32.setEstado("estado att");

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(firstEsp32)
                .put("/monitoring/esp32")
                .then();

        System.out.println(response.extract().asString());

        Esp32Dto esp32DtoResponse = response.extract().as(Esp32Dto.class);

        assertFalse(esp32DtoResponse.getConfigParams().isEmpty());
        assertNotNull(esp32DtoResponse.getId());
        assertEquals("estado att", esp32DtoResponse.getEstado());
    }

    @Test
    void updateEsp32WithIdenficadorNaoRegistradoFalha() {
        var esp32First = createEsp32();
        esp32First.setIdentificador("qualquer");

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(esp32First)
                .put("/monitoring/esp32")
                .then();

        System.out.println(response.extract().asString());
        response.body("Requisição_inválida.mensagem", equalTo("Esp32 não encontrado com identificador"));

    }

    @Test
    void consultarCo2PorEnderecoDataEntaoSucesso() {
        var firstEsp32 = createEsp32();

        createCo2Data(Integer.parseInt(firstEsp32.getIdentificador()));
        createCo2Data(Integer.parseInt(firstEsp32.getIdentificador()));
        var dataIni = LocalDateTime.now().minusDays(10);
        var dataFin = LocalDateTime.now().plusDays(10);

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(firstEsp32)
                .param("estado", firstEsp32.getEstado())
                .param("cidade", firstEsp32.getCidade())
                .param("bairro", firstEsp32.getBairro())
                .param("data_inicial", dataIni.toString())
                .param("data_final", dataFin.toString())
                .get("/monitoring/co2data")
                .then();

        System.out.println(response.extract().asString());

        List<Co2DataDto> esp32DtoResponse = response.extract().as(new TypeRef<List<Co2DataDto>>() {});
        assertTrue(esp32DtoResponse.size() == 2);

    }

    @Test
    void consultarCo2PorEnderecoDataRawTextEntaoSucesso() {

        var firstEsp32 = createEsp32();

        createCo2Data(Integer.parseInt(firstEsp32.getIdentificador()));
        createCo2Data(Integer.parseInt(firstEsp32.getIdentificador()));
        var dataIni = LocalDateTime.now().minusDays(10);
        var dataFin = LocalDateTime.now().plusDays(10);

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(firstEsp32)
                .param("estado", firstEsp32.getEstado())
                .param("cidade", firstEsp32.getCidade())
                .param("bairro", firstEsp32.getBairro())
                .param("data_inicial", dataIni.toString())
                .param("data_final", dataFin.toString())
                .get("/monitoring/co2data")
                .then();

        System.out.println(response.extract().asString());

        response.statusCode(200);

    }

    @Test
    void consultarEsp32sParaProximaManutencaoEntaoSucesso() {
        var esp32First = createEsp32();

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .get("/monitoring/esp32/consulta_proximaManutencao")
                .then();

        System.out.println(response.extract().asString());
        List<Esp32Dto> esp32Dtos = response.extract().as(new TypeRef<List<Esp32Dto>>(){});
        assertTrue(esp32Dtos.get(0).getProximaManutencao().compareTo(LocalDate.now()) < 0);
    }

    @Test
    void arimaForecast() {
        double[] data = new double[]{1,2,3,4,5,6,7,8,9,10};
        ArimaForecastRequest arima = new ArimaForecastRequest(1,1,1,1,1,1,1, 10, data);

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(arima)
                .post("/monitoring/co2data/arima")
                .then();
        System.out.println(response.extract().asString());
        ArimaForecastResponse responseAr = response.extract().as(ArimaForecastResponse.class);

        var average = Arrays.stream(responseAr.getForecastResult()).average();
        assertEquals(15.5,average.getAsDouble());
    }
}