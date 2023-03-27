package com.anton.eshop.service;

import com.anton.eshop.data.Cart;
import com.anton.eshop.data.Product;
import com.anton.eshop.data.User;
import com.anton.eshop.dto.CartDTO;
import com.anton.eshop.dto.CartDetails;
import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.repository.CartRepository;
import com.anton.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public Cart createCart(User user, List<Long> productsIds) {
        Cart cart = new Cart();
        cart.setUser(user);
        List<Product> products = getCollectRefListPProducts(productsIds);
        cart.setProducts(products);
        return cartRepository.save(cart);
    }

    @Override
    public void addProduct(Cart cart, List<Long> productsIds) {
        List<Product> products = cart.getProducts();
        List<Product> updateProducts = products == null ? new ArrayList<>()
                : new ArrayList<>(products);
        updateProducts.addAll(getCollectRefListPProducts(productsIds));
        cart.setProducts(updateProducts);
        cartRepository.save(cart);

    }

    @Override
    public CartDTO getCartByUsername(String username) {
        User user = userService.fetchUserByUsername(username);

        if (Objects.isNull(user) || user.getCart() == null) return new CartDTO();

        CartDTO cartDTO = new CartDTO();
        Map<Long, CartDetails> mapByProductId = new HashMap<>();

        List<Product> products = user.getCart().getProducts();

        for (Product product: products) {
            CartDetails cartDetails = mapByProductId.get(product.getId());
            if (cartDetails == null) {
                mapByProductId.put(product.getId(), new CartDetails(product));
            } else {

                cartDetails.setSumma(cartDetails.getSumma() + product.getPrice());
            }
        }

        cartDTO.setCartDetails(new ArrayList<>(mapByProductId.values()));
        cartDTO.aggregate();

        return cartDTO;
    }

    private List<Product> getCollectRefListPProducts(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

}
