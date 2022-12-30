package com.celtic.banking.mapping;

import com.celtic.banking.domain.Client;
import com.celtic.banking.request.ClientRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    public abstract Client mapToClient(ClientRequest clientRequest);

}
