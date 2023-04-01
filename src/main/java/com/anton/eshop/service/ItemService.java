package com.anton.eshop.service;

import com.anton.eshop.data.ItemEntity;
import com.anton.eshop.data.ProductEntity;
import com.anton.eshop.dto.ItemDTO;
import com.anton.eshop.dto.ProductDTO;

import java.util.List;

public interface ItemService {

    void create(ItemDTO itemDTO);

    ItemEntity toEntity(ItemDTO m);

    List<ItemEntity> toEntityList(List<ItemDTO> items);

    ItemDTO toModel(ItemEntity e);

    List<ItemDTO> toModelList(List<ItemEntity> items);

    void deleteById(Long item_id);
}
