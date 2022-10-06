package unip.com.inbound.adapter;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unip.com.inbound.adapter.dto.Co2DataDto;
import unip.com.inbound.adapter.dto.Esp32ConfigParamsDto;
import unip.com.inbound.adapter.dto.Esp32Dto;
import unip.com.inbound.factories.Co2DataDtoFactory;
import unip.com.inbound.factories.Esp32DtoFactory;
import unip.com.outbound.repository.Co2DataRepository;
import unip.com.outbound.repository.Esp32Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class Esp32RestAdapterTestIT {

    @Inject
    Esp32Repository esp32Repository;
    @Inject
    Co2DataRepository co2DataRepository;

    @Inject
    Co2DataDtoFactory co2DataDtoFactory;
    @Inject
    Esp32DtoFactory esp32DtoFactory;

    @BeforeEach
    @Transactional
    public void before(){
        co2DataRepository.deleteAll();
        esp32Repository.deleteAll();
    }

    @Test
    void now() {
        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .get("/esp32/now/").then();
        System.out.println(response.extract().asString());

        Instant instant = Instant.ofEpochSecond(Long.parseLong(response.extract().asString()));
    }

    public Esp32Dto createEsp32(){
        var esp = esp32DtoFactory.createEsp32(null);
        esp.setProximaManutencao(esp.getProximaManutencao().minusDays(100));
        return given().when().contentType(MediaType.APPLICATION_JSON)
                .body(esp)
                .post("/monitoring/esp32")
                .then().extract().as(Esp32Dto.class);
    }

    @Test
    void createCo2DataEntaoSucesso() {
        var fistEsp = createEsp32();
        var co2 = co2DataDtoFactory.createCo2(Integer.parseInt(fistEsp.getIdentificador()));
         var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(co2)
                .post("/esp32/data/").then();
        System.out.println(response.extract().asString());

        var co2R = response.extract().as(Co2DataDto.class);
        assertNotNull(co2R.getId());
        assertNotNull(co2R.getSensorData());
    }

    @Test
    void createCo2DataComIdentificadorInvalidoFalha() {
        var co2 = co2DataDtoFactory.createCo2(1234567890);
        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .body(co2)
                .post("/esp32/data/").then();
        System.out.println(response.extract().asString());

        response.body("Requisição_inválida.mensagem", equalTo("Esp32 não cadastrado"));

    }

    @Test
    void getConfigParamsActive() {
        var fistEsp = createEsp32();

        var response = given().when().contentType(MediaType.APPLICATION_JSON)
                .param("identificador", fistEsp.getIdentificador())
                .get("/esp32/configParams/").then();
        System.out.println(response.extract().asString());

        var responseOb = response.extract().as(new TypeRef<List<Esp32ConfigParamsDto>>(){});
        assertTrue(responseOb.size() > 0);
    }
}