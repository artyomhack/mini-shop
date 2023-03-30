package com.anton.eshop.dto;

public class AmountDetails {
    private Integer amountValue;

    public AmountDetails(Integer amountValue) {
        this.amountValue = amountValue;
    }

    public Integer getAmountValue() {
        return amountValue;
    }

    public void addAmount() {
        amountValue++;
    }

    public void clearAmount() {
        amountValue--;
    }

    public static AmountDetails of(String number) {
        return new AmountDetails(Integer.parseInt(number));
    }

}
