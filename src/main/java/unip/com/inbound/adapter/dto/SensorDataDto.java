package unip.com.inbound.adapter.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorDataDto {

    private String erros;


    @NotNull(message = "co2 não encontrado!")
    private Integer co2;

    @NotNull(message = "tvoc não encontrado!")
    private Integer tvoc;

    @NotNull(message = "temperatura não encontrada!")
    private Double temperatura;

    @NotNull(message = "umidade não encontrada!")
    private Integer umidade;

    @JsonSetter("erros")
    public void setErros(Object erros){
        if(Objects.nonNull(erros)){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                this.erros = objectMapper.writeValueAsString(erros);
            } catch (Exception e) {
                this.erros = null;
            }
        }else this.erros = null;

    }
}
