package com.celtic.banking.rest;

import com.celtic.banking.request.ClientResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestTemplateCommunication {
    public static void main(String[] args) {
        String findClientByIdURL = "http://localhost:8080/celtic-banking/clients/{id}";
        String findAllClientsURL = "http://localhost:8080/celtic-banking/clients";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ClientResponse> responseForEntity = restTemplate.getForEntity(findClientByIdURL, ClientResponse.class, 21);

        ClientResponse responseForObject = restTemplate.getForObject(findClientByIdURL, ClientResponse.class, 18);

        Object[] responseForObjectArray = restTemplate.getForObject(findAllClientsURL, ClientResponse[].class, 18);

        ResponseEntity<List<ClientResponse>> responseExchange = restTemplate.exchange(findAllClientsURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientResponse>>() {
        });

        System.out.println(responseForEntity);
        System.out.println(responseForObject);
        System.out.println(Arrays.toString(responseForObjectArray));
        System.out.println((responseExchange));

    }
}
