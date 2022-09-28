package br.com.celtic.bank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    @JsonIgnore
    private Long id;
    @JsonProperty("client_name")
    private String fullName;
    @JsonProperty("client_cpf")
    private String cpf;
}
