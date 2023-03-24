package com.anton.eshop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CartDetailsDTO {
    private String title;
    private Long productId;
    private double price;
    private double amount;
    private double summa;

    public CartDetailsDTO(String title, Long productId, double price, double amount, double summa) {
        this.title = title;
        this.productId = productId;
        this.price = price;
        this.amount = amount;
        this.summa = summa;
    }
}
