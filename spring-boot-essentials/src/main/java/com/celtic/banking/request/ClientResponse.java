package com.celtic.banking.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;
}
