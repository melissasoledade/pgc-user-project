package com.user.application.mappers.event;

import com.user.application.models.event.UserAddressEvent;
import com.user.domain.entities.Address;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAddressEventMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = { "addressId", "createdAt", "updatedAt" })
    UserAddressEvent fromAddress(Address address);

}
