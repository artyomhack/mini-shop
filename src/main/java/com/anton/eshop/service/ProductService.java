package com.anton.eshop.service;

import com.anton.eshop.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDTO> fetchAll();
}
