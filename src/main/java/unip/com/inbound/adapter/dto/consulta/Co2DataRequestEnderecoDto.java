package unip.com.inbound.adapter.dto.consulta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class Co2DataRequestEnderecoDto {

    @NotNull(message = "pais não encontrado")
    private String pais;

    @NotNull(message = "cidade não encontrado")
    private String cidade;

    @NotNull(message = "municipio não encontrado")
    private String municipio;

    @NotNull(message = "bairro não encontrado")
    private String bairro;

    @NotNull(message = "data_inicial não encontrado")
    @JsonProperty("data_inicial")
    private LocalDate dataInicial;

    @NotNull(message = "data_final não encontrado")
    @JsonProperty("data_final")
    private LocalDate dataFinal;
}
