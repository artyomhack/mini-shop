package com.anton.eshop.dto;

import com.anton.eshop.data.OrderDetails;
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
    private List<CartDetailsDTO> cartDetailsDTOS = new ArrayList<>();

    public void aggregate() {
        this.amountProducts = cartDetailsDTOS.size();
        this.summa = cartDetailsDTOS.stream()
                .map(CartDetailsDTO::getSumma)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
