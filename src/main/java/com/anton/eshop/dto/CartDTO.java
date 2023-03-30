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
    private Long id;
    private AmountDetails amount;
    private double summa;
    private List<CartDetails> cartDetails = new ArrayList<>();

    public void aggregate() {
        this.amount = AmountDetails.of(String.valueOf(cartDetails.size()));
        this.summa = cartDetails.stream()
                .map(CartDetails::getSumma)
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println("summa: " + summa);
    }
}
