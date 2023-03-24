package com.anton.eshop.controller;

import com.anton.eshop.dto.CartDTO;
import com.anton.eshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
public class CartController {
    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String aboutCart(Model model, Principal principal) {
        if (Objects.isNull(principal))  model.addAttribute("cart", new CartDTO());
        else {
            CartDTO cartDTO = cartService.getCartByUsername(principal.getName());
            model.addAttribute("cart", cartDTO);
        }
        return "cart";
    }
}
