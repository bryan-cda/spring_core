package com.celtic.banking.rest;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientRequest;
import com.celtic.banking.request.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Slf4j
public class RestTemplateCommunication {
    public static void main(String[] args) {
        String findClientByIdURL = "http://localhost:8080/celtic-banking/clients/{id}";
        String baseClientAPIURL = "http://localhost:8080/celtic-banking/clients";
        String deleteClientByIdURL = "http://localhost:8080/celtic-banking/clients/{id}";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ClientResponse> responseForEntity = restTemplate.getForEntity(findClientByIdURL, ClientResponse.class, 21);

        ClientResponse responseForObject = restTemplate.getForObject(findClientByIdURL, ClientResponse.class, 18);

        Object[] responseForObjectArray = restTemplate.getForObject(baseClientAPIURL, ClientResponse[].class, 18);

        ResponseEntity<List<ClientResponse>> responseExchange = restTemplate.exchange(baseClientAPIURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientResponse>>() {
        });

        ClientRequest newClient = ClientRequest.builder().firstName("bar").lastName("foo").cpf("111.000.111-00").build();

        ClientRequest updatedClient = ClientRequest.builder().id(44L).firstName("foo").lastName("bar").cpf("555.555.555-55").build();

        ClientResponse createClientResponse = restTemplate.postForObject(baseClientAPIURL, newClient, ClientResponse.class);

        ResponseEntity<ClientResponse> updateClientResponse = restTemplate.exchange(baseClientAPIURL, HttpMethod.PUT, new HttpEntity<>(updatedClient, jsonHeader()), ClientResponse.class);

        ResponseEntity<ClientResponse> creationClientExchange = restTemplate.exchange(baseClientAPIURL, HttpMethod.POST, new HttpEntity<>(newClient), ClientResponse.class);

        restTemplate.exchange(deleteClientByIdURL, HttpMethod.DELETE, null, void.class, 44);


        log.info(responseForEntity.toString());
        log.info(responseForObject.toString());
        log.info(Arrays.toString(responseForObjectArray));
        log.info(responseExchange.toString());
        log.info(createClientResponse.toString());
        log.info(creationClientExchange.toString());
        log.info(updateClientResponse.toString());
    }

    private static HttpHeaders jsonHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
