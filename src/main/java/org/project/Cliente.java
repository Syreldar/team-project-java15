package org.project;

public class Cliente {

    private String firstName;
    private String lastName;
    private double balance;

    public Cliente(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
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

     // metodo acquista dove il cliente può acquistare a seconda del saldo disponibile con relativo importo rimanente dopo l'acquisto
    public void buyProduct(Shop shop, Product product) {
        double price = product.getPrice();
        if (balance < price) {
            System.out.println("Saldo insufficiente per l'acquisto. Saldo attuale: " + balance);
            return;
        }

        // Verifica se il prodotto esiste nel negozio
        if (!shop.containsProduct(product)) {
            System.out.println("Il prodotto non è disponibile nel negozio.");
            return;

        }
        balance -= products.getPrice();
        shop.sellProduct(product, price);
        System.out.printf("Acquisto di %s presso il negozio %s effettuato con successo. Saldo attuale: %f. %n",
                product.getName(), shop.getName(), balance);

    }
        @Override
    public String toString() {
        return  String.format("Cliente %s %s %f", firstName, lastName, balance);
    }
}
