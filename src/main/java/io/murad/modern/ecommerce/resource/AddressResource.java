package io.murad.modern.ecommerce.resource;


import io.murad.modern.ecommerce.dto.AddressDto;
import io.murad.modern.ecommerce.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/addresses")
@AllArgsConstructor
public class AddressResource {

    private final AddressService addressService;

    @PostMapping()
    public ResponseEntity<?> createdAddress(@RequestBody AddressDto addressDto) {
        addressService.addAddress(addressDto);
        return new ResponseEntity<>("Address created", HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "address",key = "#id")
    public ResponseEntity<AddressDto> getAddress(@PathVariable("id") Long id) {
        return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
    }

    @GetMapping()
    @Cacheable(value = "addresses")
    public ResponseEntity<List<AddressDto>> getAddressList() {
        return new ResponseEntity<>(addressService.listAddresses(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editAddress(@PathVariable("id") Long id, @RequestBody AddressDto addressDto) {
        addressService.updateAddress(id, addressDto);
        return new ResponseEntity<>("Address updated", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address removed.", HttpStatus.OK);
    }
}
