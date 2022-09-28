package br.com.celtic.bank.response;

import br.com.celtic.bank.domain.Balance;
import br.com.celtic.bank.domain.Client;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientBalanceResponse {
    Client clientData;
    Balance balanceData;
}
