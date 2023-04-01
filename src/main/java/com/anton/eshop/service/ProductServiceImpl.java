package com.anton.eshop.service;

import com.anton.eshop.data.ProductEntity;
import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.dto.mapDTO.ProductMapper;
import com.anton.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper = new ProductMapper();

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(ProductDTO productDTO) {
        productRepository.save(mapper.productDTOmapToProduct(productDTO));
    }

    @Override
    public Iterable<ProductEntity> getAllProducts() {
        return null;
    }

    @Override
    public List<ProductDTO> fetchAll() {
        return mapper.productsToProductsDTO(productRepository.findAll());
    }

    @Override
    public ProductEntity getProductById(String id) {
        return null;
    }

    @Override
    public void deleteById(Long product_id) {

    }
}
