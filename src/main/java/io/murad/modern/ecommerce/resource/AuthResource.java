package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.dto.AuthenticationRequest;
import io.murad.modern.ecommerce.dto.AuthenticationResponse;
import io.murad.modern.ecommerce.dto.RefreshTokenRequest;
import io.murad.modern.ecommerce.dto.RegisterRequest;
import io.murad.modern.ecommerce.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

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

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authService.signIn(authenticationRequest);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/refresh/token")
    public ResponseEntity<AuthenticationResponse> refreshTokens(@RequestBody RefreshTokenRequest refreshTokenRequest) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        AuthenticationResponse authenticationResponseFromRefreshToken = authService.refreshToken(refreshTokenRequest);
        return new ResponseEntity<>(authenticationResponseFromRefreshToken, HttpStatus.OK);
    }
}
