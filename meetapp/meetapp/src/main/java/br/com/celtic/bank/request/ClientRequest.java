package br.com.celtic.bank.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientRequest {
    private String agency;
    private String account;
}
