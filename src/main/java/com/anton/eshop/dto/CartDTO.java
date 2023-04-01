package com.anton.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private Integer amount;
    private BigDecimal summa;
    private List<ItemDTO> items = new ArrayList<>();

    public CartDTO() {
        this.amount = items.size();
        this.summa = BigDecimal.valueOf(items.stream()
                .map(ItemDTO::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum());
        System.out.println("summa:" + summa);
    }
}
