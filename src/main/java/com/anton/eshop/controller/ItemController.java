package com.anton.eshop.controller;

import com.anton.eshop.service.CartService;
import com.anton.eshop.service.ItemService;
import com.anton.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
    private final ItemService itemService;
    private final ProductService productService;
    private final CartService cartService;

    public ItemController(ItemService itemService, ProductService productService, CartService cartService) {
        this.itemService = itemService;
        this.productService = productService;
        this.cartService = cartService;
    }
}
