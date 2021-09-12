package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Address;
import io.murad.modern.ecommerce.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "user", source = "")
    @Mapping(target = "postalCode", source = "addressDto.zipcode")
    @Mapping(target = "country", source = "addressDto.countryName")
    public Address mapToAddress(AddressDto addressDto);

    @Mapping(target = "zipcode", source = "address.postalCode")
    @Mapping(target = "countryName", expression = "java(address.country.getCountryName())")
    public AddressDto mapAddressToDto(Address address);
}
