package com.celtic.banking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientRequest {
    private String firstName;

    private String lastName;

    private String cpf;
}
