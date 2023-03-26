package com.anton.eshop.dto;


import com.anton.eshop.data.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
public class CartDetails {
    private String title;
    private Long productId;
    private Product product;
    private double price;
    private BigDecimal amount;
    private double summa;

    public CartDetails(String title, Long productId, Product product, double price, BigDecimal amount, double summa) {
        this.title = title;
        this.productId = productId;
        this.product = product;
        this.price = price;
        this.amount = amount;
        this.summa = summa;
    }

    public CartDetails(Product it) {
        this.product = it;
    }
}
