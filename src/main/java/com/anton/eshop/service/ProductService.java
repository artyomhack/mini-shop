package com.anton.eshop.service;

import com.anton.eshop.data.ProductEntity;
import com.anton.eshop.dto.ProductDTO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface ProductService {
    void create(ProductDTO productDTO);

    Iterable<ProductEntity> getAllProducts();

    List<ProductDTO> fetchAll();

    ProductEntity getProductById(@Min(value = 1L, message = "Invalid product ID") String id);

    void deleteById(Long product_id);
}
