package br.com.kafka.example.producer.application.adapters.inbound.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class AgendarVisitaRequest {

    @JsonProperty("data")
    private String data;
    @JsonProperty("horario")
    private String horario;
    @JsonProperty("usuario")
    private UsuarioRequest usuario;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties
    public class UsuarioRequest {

        @JsonProperty("cpf")
        private String cpf;

        @JsonProperty("nome")
        private String nome;
        @JsonProperty("email")
        private String email;

    }

}
