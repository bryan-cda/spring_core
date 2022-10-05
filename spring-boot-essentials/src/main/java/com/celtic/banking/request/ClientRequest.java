package com.celtic.banking.request;

import lombok.Data;

@Data
public class ClientRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
}
