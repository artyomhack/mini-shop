package com.anton.eshop.dto.mapDTO;

import com.anton.eshop.data.Cart;
import com.anton.eshop.data.Product;
import com.anton.eshop.dto.CartDTO;
import com.anton.eshop.dto.ProductDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CartMapper {
    public static CartMapper MAPPER = new CartMapper();

    public CartDTO cartMapCartDTO(Cart cart) {
        return CartDTO.builder()
                .amountProducts(cart.getProducts().size())


    }

    public Product productDTOmapToProduct(ProductDTO productDTO) {
        if (Objects.nonNull(productDTO)) {
            return Product.builder()
                    .id(productDTO.getId())
                    .title(productDTO.getTitle())
                    .price(productDTO.getPrice())
                    .build();
        } else
            return null;
    }

    public List<ProductDTO> productsToProductsDTO(List<Product> products) {
        return products.stream()
                .map(this::productMapProductDTO)
                .collect(Collectors.toList());
    }

    public List<Product> productsDTOtoProducts(List<ProductDTO> productsDTO) {
        return productsDTO.stream()
                .map(this::productDTOmapToProduct)
                .collect(Collectors.toList());
    }
}
