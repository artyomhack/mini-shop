package com.anton.eshop.dto.mapDTO;

import com.anton.eshop.dto.ItemDTO;
import com.anton.eshop.dto.ProductDTO;

public class ItemMapper {
    private static final ItemMapper MAPPER = new ItemMapper();

    public ItemDTO productMapToItemModel(ProductDTO productDTO) {
        return new ItemDTO(
                productDTO.getId(),
                productDTO.getTitle(),
                productDTO.getPrice(),
                productDTO.getAmount()
        );
    }
}
