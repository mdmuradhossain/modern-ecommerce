package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.service.AuthService;
import io.murad.modern.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/orders")
@AllArgsConstructor
public class OrderResource {

    private final OrderService orderService;
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<?> placeOrder(@RequestParam("sessionId") String sessionId) {
        User user = authService.getCurrentUser();
        orderService.checkOut(user, sessionId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
