package io.murad.modern.ecommerce.mapper;

import io.murad.modern.ecommerce.database.model.Address;
import io.murad.modern.ecommerce.database.model.Country;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id",source = "addressDto.id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "postalCode", source = "addressDto.zipcode")
    @Mapping(target = "country", source = "country")
    public Address mapToAddress(AddressDto addressDto, Country country ,User user);

    @Mapping(target = "zipcode", source = "address.postalCode")
//    @Mapping(target = "countryName", expression = "java(address.getCountry().getCountryName())")
    public AddressDto mapAddressToDto(Address address);
}
