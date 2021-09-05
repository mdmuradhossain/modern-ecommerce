package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.database.model.Product;
import io.murad.modern.ecommerce.database.model.User;
import io.murad.modern.ecommerce.dto.AddToCartDto;
import io.murad.modern.ecommerce.dto.CartDto;
import io.murad.modern.ecommerce.service.CartService;
import io.murad.modern.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/carts")
@AllArgsConstructor
public class CartResource {

    private final CartService cartService;
    private final ProductService productService;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartDto addToCartDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Product product = productService.getProduct(addToCartDto.getProductId());
        log.info("product to add"+  product.getName());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping(path = "/")
    public ResponseEntity<CartDto> getCartItems(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }
}
