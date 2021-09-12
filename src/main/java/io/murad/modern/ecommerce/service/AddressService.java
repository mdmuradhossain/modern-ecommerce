package io.murad.modern.ecommerce.service;

import io.murad.modern.ecommerce.database.model.Address;
import io.murad.modern.ecommerce.database.model.Country;
import io.murad.modern.ecommerce.dto.AddressDto;
import io.murad.modern.ecommerce.exception.AddressNotFoundException;
import io.murad.modern.ecommerce.mapper.AddressMapper;
import io.murad.modern.ecommerce.repository.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CountryRepository countryRepository;

    public void addAddress(AddressDto addressDto) {
        addressRepository.save(addressMapper.mapToAddress(addressDto));
        log.info("Address Saved..");
    }

    public AddressDto getAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("Address not found"));
        return addressMapper.mapAddressToDto(address);
    }

    public List<AddressDto> listAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::mapAddressToDto)
                .collect(Collectors.toList());
    }

    public void updateAddress(Long id, AddressDto addressDto) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("Address not found"));
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPostalCode(addressDto.getZipcode());

        Country country = countryRepository.findByCountryName(addressDto.getCountryName());
        address.setCountry(country);
        log.info("Address Updated.");
    }

    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
        log.info("Address deleted");
    }
}
