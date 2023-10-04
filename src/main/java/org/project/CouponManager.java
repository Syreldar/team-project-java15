package org.project;

import java.math.BigDecimal;
import java.util.List;

public class CouponManager {
    private List<Coupon> coupons;

    public void addCoupon(Coupon coupon) {
    }

    public void removeCoupon(String code) {
    }

    public static BigDecimal applyCoupon(BigDecimal originalPrice, String code) {
        return originalPrice;
    }
}