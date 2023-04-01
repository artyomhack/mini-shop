package com.anton.eshop.dto.mapDTO;

import com.anton.eshop.data.ProductEntity;
import com.anton.eshop.dto.ProductDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductMapper MAPPER = new ProductMapper();

    public ProductDTO productMapProductDTO(ProductEntity productEntity) {
            return ProductDTO.builder()
                    .id(productEntity.getId())
                    .title(productEntity.getTitle())
                    .price(productEntity.getPrice())
                    .amount(productEntity.getCount())
                    .build();

    }

    public ProductEntity productDTOmapToProduct(ProductDTO productDTO) {
        if (Objects.nonNull(productDTO)) {
            return ProductEntity.builder()
                    .id(productDTO.getId())
                    .title(productDTO.getTitle())
                    .price(productDTO.getPrice())
                    .count(productDTO.getAmount())
                    .build();
        } else
            return null;
    }

    public List<ProductDTO> productsToProductsDTO(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(this::productMapProductDTO)
                .collect(Collectors.toList());
    }

    public List<ProductEntity> productsDTOtoProducts(List<ProductDTO> productsDTO) {
        return productsDTO.stream()
                .map(this::productDTOmapToProduct)
                .collect(Collectors.toList());
    }

}
