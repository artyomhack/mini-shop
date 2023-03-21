package com.anton.eshop.service;

import com.anton.eshop.data.Product;
import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.dto.mapDTO.ProductMapper;
import com.anton.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> fetchAll() {
        return mapper.productsToProductsDTO((List<Product>) productRepository.findAll());
    }
}
