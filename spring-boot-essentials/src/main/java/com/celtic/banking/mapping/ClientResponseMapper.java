package com.celtic.banking.mapping;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClientResponseMapper {
    public static final ClientResponseMapper INSTANCE = Mappers.getMapper(ClientResponseMapper.class);

    public abstract ClientResponse matToClientResponse(Client client);
}
