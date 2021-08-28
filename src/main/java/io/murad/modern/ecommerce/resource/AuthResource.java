package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.dto.RegisterRequest;
import io.murad.modern.ecommerce.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthResource {

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.CREATED);
    }

    @GetMapping(path = "/accountVerification/{token}")
    public ResponseEntity<String> verifyAccountWithEmail(@PathVariable("token") String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("User Activated Successfully.", HttpStatus.OK);
    }
}
