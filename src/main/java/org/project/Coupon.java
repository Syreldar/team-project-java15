package org.project;

import java.math.BigDecimal;

public class Coupon {
    private String code;
    private BigDecimal discountValue;


    public Coupon(String code, BigDecimal discountValue) {
        this.code = code;
        this.discountValue = discountValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }
}