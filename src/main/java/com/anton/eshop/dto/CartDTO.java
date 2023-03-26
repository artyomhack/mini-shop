package com.anton.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private int amountProducts;
    private double summa;
    private List<CartDetails> cartDetails = new ArrayList<>();

    public void aggregate() {
        this.amountProducts = cartDetails.size();
        this.summa = cartDetails.stream()
                .map(CartDetails::getSumma)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
