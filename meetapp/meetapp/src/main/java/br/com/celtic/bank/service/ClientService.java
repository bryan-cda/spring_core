package br.com.celtic.bank.service;

import br.com.celtic.bank.request.ClientRequest;
import br.com.celtic.bank.response.ClientBalanceResponse;
import org.springframework.stereotype.Service;
@Service
public class ClientService {


    public ClientBalanceResponse listClients() {
        return null;
    }

    public ClientBalanceResponse findClientById(Long id){
        return null;
    }

    public void createAccount(ClientRequest clientRequest) {
    }

    public void closeAccount(Long id) {
    }

    public ClientBalanceResponse updateClientDataAccount(ClientRequest clientRequest) {
        return null;
    }
}
