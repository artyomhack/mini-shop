package com.anton.eshop.dto;


import com.anton.eshop.data.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetails {
    private String title;
    private Long productId;
    private double price;
    private AmountDetails amount;
    private double summa;

    public CartDetails(Product product) {
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.amount = AmountDetails.of(String.valueOf(product.getAmount()));
        this.summa = product.getPrice();
    }
}
