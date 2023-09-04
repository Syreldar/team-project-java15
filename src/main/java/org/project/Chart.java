package org.project;

import java.util.ArrayList;
import java.util.List;

public class Chart {
    private final List<Shop> shops;

    public Chart() {
        this.shops = new ArrayList<>();
    }

    public void addShop(Shop shop) {
        shops.add(shop);
    }

    public List<Shop> getShopsByGains() {
        shops.sort((n1, n2) -> Double.compare(n2.getTotalGains(), n1.getTotalGains()));
        return new ArrayList<>(shops);
    }

    public List<Shop> getShopsByCategory(Category category) {
        List<Shop> filteredShops = new ArrayList<>();
        for (Shop shop : shops) {
            if (category.equals(shop.getMostSoldCategory())) {
                filteredShops.add(shop);
            }
        }

        filteredShops.sort((n1, n2) -> Double.compare(n2.getTotalGains(), n1.getTotalGains()));
        return filteredShops;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chart [\n");
        for (Shop shop : shops) {
            sb.append("  ").append(shop.toString()).append(",\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
