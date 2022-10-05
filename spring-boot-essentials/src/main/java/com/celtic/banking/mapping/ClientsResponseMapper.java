package com.celtic.banking.mapping;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClientsResponseMapper {
    public static final ClientsResponseMapper INSTANCE = Mappers.getMapper(ClientsResponseMapper.class);

    public abstract List<ClientResponse> mapToListOfClientResponse(List<Client> clients);

}
