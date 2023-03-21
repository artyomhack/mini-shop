package com.anton.eshop.controller;

import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productsList(Model model) {
        List<ProductDTO> list = productService.fetchAll();
        model.addAttribute("products", list);

        return "products";
    }

}
