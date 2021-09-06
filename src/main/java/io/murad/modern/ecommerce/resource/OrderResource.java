package io.murad.modern.ecommerce.resource;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.CheckoutItemDto;
import io.murad.modern.ecommerce.dto.StripeResponse;
import io.murad.modern.ecommerce.service.AuthService;
import io.murad.modern.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<StripeResponse>(stripeResponse,HttpStatus.OK);
    }
}
