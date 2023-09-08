package org.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chart {
    private final List<Shop> shops;

    public Chart() {
        this.shops = new ArrayList<>();
    }

    public void addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("The shop cannot be null");
        }

        this.shops.add(shop);
        System.out.printf("Chart: Shop %s by %s added!%n", shop.getName(), shop.getOwnerName());
    }

    public List<Shop> getShopsBySells() {
        this.shops.sort((shop1, shop2) -> Double.compare(shop2.getTotalSales(), shop1.getTotalSales()));
        return Collections.unmodifiableList(this.shops);
    }

    public List<Shop> getShopsByGains() {
        this.shops.sort((n1, n2) -> n2.getTotalGains().compareTo(n1.getTotalGains()));
        return Collections.unmodifiableList(this.shops);
    }

    public List<Shop> getShopsByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("The category cannot be null");
        }

        List<Shop> filteredShops = new ArrayList<>();
        for (Shop shop : this.shops) {
            if (category.equals(shop.getMostSoldCategory())) {
                filteredShops.add(shop);
            }
        }

        filteredShops.sort((n1, n2) -> n2.getTotalGains().compareTo(n1.getTotalGains()));
        return Collections.unmodifiableList(filteredShops);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chart [\n");
        for (Shop shop : this.shops) {
            sb.append("  ").append(shop.toString()).append(",\n");
        }
        sb.append("]");
        return sb.toString();
    }
}
