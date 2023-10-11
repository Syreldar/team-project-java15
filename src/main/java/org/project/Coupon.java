package org.project;

public class Coupon {
    private final String code;
    private final double discountAmount;

    public Coupon(String code, double discountAmount) {
        if (discountAmount < 0 || discountAmount > 1) {
            throw new IllegalArgumentException("Lo sconto deve essere compreso tra 0 e 1, in cui 1 è 100% e 0 è nessuno sconto ");
        }
        this.code = code;
        this.discountAmount = discountAmount;
    }

    public String getCode() {
        return code;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

}
