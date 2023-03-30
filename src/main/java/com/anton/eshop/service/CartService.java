package com.anton.eshop.service;

import com.anton.eshop.data.Cart;
import com.anton.eshop.data.User;
import com.anton.eshop.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Cart createCart(User user, List<Long> productsIds);

    void addProduct(Cart cart, List<Long> productsIds);

    CartDTO getCartByUsername(String username);

    void deleteProductByCartIdAndProductId(Long cart_id, Long product_id);
}
