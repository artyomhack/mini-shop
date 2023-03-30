package com.anton.eshop.service;

import com.anton.eshop.data.Cart;
import com.anton.eshop.data.Product;
import com.anton.eshop.data.User;
import com.anton.eshop.dto.CartDTO;
import com.anton.eshop.dto.CartDetails;
import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.dto.mapDTO.ProductMapper;
import com.anton.eshop.dto.mapDTO.UserMapper;
import com.anton.eshop.repository.ProductRepository;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper = ProductMapper.MAPPER;
    private final UserMapper userMapper = UserMapper.MAPPER;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final CartService cartService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CartService cartService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public List<ProductDTO> fetchAll() {
        return productMapper.productsToProductsDTO(productRepository.findAll());
    }

    @Override
    public ProductDTO fetchId(Long id) {
        ProductDTO productDTO = new ProductDTO();
        for (Product product : productRepository.findAll()) {
            if (product.getId().equals(id)) {
                productDTO = productMapper.productMapProductDTO(product);
                break;
            }
        }

        return productDTO;
    }

    @Override
    @Transactional
    public void addUserToCart(String username, Long productId) {
        User user = userService.fetchUserByUsername(username);
        if (user == null) throw new RuntimeException();

        Cart cart = user.getCart();
        if (Objects.isNull(cart)) {
            Cart newCart = cartService.createCart(user, Collections.singletonList(productId));
            user.setCart(newCart);
            userService.save(userMapper.userMapUserDTO(user));
        } else {
            cartService.addProduct(cart, Collections.singletonList(productId));
        }
    }

    @Override
    public void create(ProductDTO productDTO) {
        if (Objects.nonNull(productDTO)) {
            Product product = Product.builder()
                    .id(productDTO.getId())
                    .title(productDTO.getTitle())
                    .amount(productDTO.getAmount())
                    .price(productDTO.getPrice())
                    .categories(new ArrayList<>())
                    .build();
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product is null");
        }
    }
}
