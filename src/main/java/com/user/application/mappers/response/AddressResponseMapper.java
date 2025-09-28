package com.user.application.mappers.response;

import com.user.application.dto.response.AddressResponseDTO;
import com.user.domain.entities.Address;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = { "addressId", "createdAt", "updatedAt" })
    AddressResponseDTO fromAddress(Address address);
}
