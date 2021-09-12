package io.murad.modern.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String countryName;
}
