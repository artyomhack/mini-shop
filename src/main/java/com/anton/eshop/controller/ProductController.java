package com.anton.eshop.controller;

import com.anton.eshop.dto.CartDTO;
import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.service.CartService;
import com.anton.eshop.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
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

    @GetMapping("//{product_id}")
    public String deleteProduct(
            @PathVariable(name = "product_id") Long product_id,
                                Principal principal) {
        ProductDTO product = productService.fetchId(product_id);
        if (Objects.isNull(product) ) {
            return "redirect:/products";
        }

        productService.deleteProductByCartIdAndProductId(product_id, principal.getName());
        return "redirect:/products";
    }

}
