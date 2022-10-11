package com.celtic.banking.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class ClientRequest {
    private Long id;
    @NotEmpty(message = "The first name cannot be blank")
    private String firstName;
    @NotEmpty(message = "The last name cannot be blank")
    private String lastName;
    @NotEmpty(message = "The CPF cannot be blank")
    private String cpf;
}
