package io.murad.modern.ecommerce.resource;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import io.murad.modern.ecommerce.database.model.Order;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.CheckoutItemDto;
import io.murad.modern.ecommerce.dto.StripeResponse;
import io.murad.modern.ecommerce.repository.UserRepository;
import io.murad.modern.ecommerce.service.AuthService;
import io.murad.modern.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/orders")
public class OrderResource {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<?> placeOrder(@RequestParam("sessionId") String sessionId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).get();
        orderService.checkOut(user, sessionId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrders(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).get();
        List<Order> orderDtoList = orderService.listOrders(user);
        return new ResponseEntity<List<Order>>(orderDtoList, HttpStatus.OK);
    }
}
