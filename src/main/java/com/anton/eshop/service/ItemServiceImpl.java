package com.anton.eshop.service;

import com.anton.eshop.data.ItemEntity;
import com.anton.eshop.data.ProductEntity;
import com.anton.eshop.dto.ItemDTO;
import com.anton.eshop.dto.ProductDTO;
import com.anton.eshop.dto.mapDTO.ItemMapper;
import com.anton.eshop.repository.ItemRepository;
import com.anton.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final ItemMapper mapper = new ItemMapper();

    public ItemServiceImpl(ItemRepository itemRepository, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void create(ItemDTO itemDTO) {
        itemRepository.save(toEntity(itemDTO));
    }

    @Override
    public ItemEntity toEntity(ItemDTO m) {
        ItemEntity entity = new ItemEntity();
        ProductEntity product = ProductEntity.builder()
                .id(m.getId())
                .price(m.getPrice())
                .count(m.getQuantity())
                .build();
        entity.setProduct(product);
        return entity;
    }

    @Override
    public List<ItemEntity> toEntityList(List<ItemDTO> items) {
        if (Objects.isNull(items)) return Collections.emptyList();
        return items.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public ItemDTO toModel(ItemEntity e) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(e.getProduct().getId());
        itemDTO.setPrice(e.getProduct().getPrice());
        itemDTO.setQuantity(e.getProduct().getCount());
        itemDTO.setTitle(e.getProduct().getTitle());
        return itemDTO;
    }

    @Override
    public List<ItemDTO> toModelList(List<ItemEntity> items) {
        if (Objects.isNull(items)) return  Collections.emptyList();
        return items.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long item_id) {
        itemRepository.deleteById(item_id);
    }
}
