package com.anton.eshop.controller;

import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.service.CartService;
import com.anton.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping
    public String productsList(Model model) {
        List<ProductDTO> list = productService.fetchAll();
        model.addAttribute("products", list);

        return "products";
    }

    @GetMapping("/{id}/cart")
    public String addCart(@PathVariable(name = "id") Long id, Principal principal) {
        if (Objects.isNull(principal))
            return "redirect:/products";

        productService.addUserToCart(principal.getName(), id);
        return "redirect:/products";
    }

    @GetMapping(value = "/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        ProductDTO productDTO = productService.fetchId(id);
        if (Objects.isNull(productDTO))
            return "redirect:/products";

        cartService.deleteProductAndCartByProductId(id);
        return "redirect:/products";
    }


}
