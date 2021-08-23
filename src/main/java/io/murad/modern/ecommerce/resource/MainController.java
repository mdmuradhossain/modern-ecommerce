package io.murad.modern.ecommerce.resource;

import io.murad.modern.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping("/main")
    public String mainPage(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("products", productService.getAllProducts());
        return "main";
    }
}
