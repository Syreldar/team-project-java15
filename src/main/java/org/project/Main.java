package org.project;

import org.project.database.Database;
import org.project.charts.Chart;
import org.project.factory.EntityFactory;
import org.project.models.Customer;
import org.project.models.Shop;
import org.project.utils.Utilities;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Chart chart = new Chart();
        EntityFactory factory = new EntityFactory(database, chart);

        factory.createShops(List.of(
                new Shop("ShopA", "Carlo"),
                new Shop("ShopB", "Para"),
                new Shop("ShopC", "Malo")
        ));

        factory.createCustomer(new Customer("Enrico", "Drago", 24.52));
        factory.createCustomer(new Customer("Enrico", "Drago", 12.71));

        Utilities.MainMenu(factory, database);
    }
}

