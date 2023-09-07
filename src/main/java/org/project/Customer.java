package org.project;

public class Customer {
    private String firstName;
    private String lastName;
    private double balance;

    public Customer(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void buyProduct(Shop shop, Product product) {
        double price = product.getPrice();
        if (this.balance < price) {
            System.out.printf("Insufficient balance to purchase. Current balance: %.2f%n", this.balance);
            return;
        }

        if (!shop.containsProduct(product)) {
            System.out.println("The product is not available in the shop.");
            return;
        }

        this.balance -= price;
        shop.sellProduct(product, price);
        System.out.printf("Purchase of %s from shop %s successful. Current balance: %.2f%n",
                product.getName(), shop.getName(), this.balance);
    }
    
    public void viewHistoryChart(Chart chart) {
        // itera attraverso la lista degli ordini completati nel Chart
        for (Shop shop : chart.getShopByGains()) {
            System.out.println("Negozio: " + shop.getName());

            // Ottieni la lista degli ordini completati dal negozio
            List<Order> completedOrders = shop.getCompletedOrders();

            if (completedOrders.isEmpty()) {
                System.out.println("Nessun ordine completato.");
                return;
            }
            if (completedOrders.size() > 1) {
                System.out.println("Ordini completati:");
                return;

                // Itera attraverso la lista degli ordini completati e visualizza le informazioni di ciascun ordine
                for (Order order : completedOrders) {
                    System.out.println("- Negozio: " + shop.getName());
                    System.out.println("- Data dell'ordine: " + order.getOrderDate());
                    System.out.println("- Importo dell'ordine: " + order.getPrice());
                }
            }
        }
    }
    @Override
    public String toString() {
        return String.format("Customer [FirstName: %s, LastName: %s, Balance: %.2f]",
                this.firstName, this.lastName, this.balance);
    }
}
