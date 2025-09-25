package com.user.application.mappers;

import com.user.application.dto.AddressDTO;
import com.user.domain.entities.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "addressId", ignore = true)
    Address toAddress(AddressDTO addressDTO);

    @InheritInverseConfiguration
    AddressDTO fromAddress(Address address);
}
