package org.example;

public class Cliente {
    private String firstName;
    private String lastName;
    private double saldo;

    public Cliente(String firstName, String lastName, double saldo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.saldo = saldo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // metodo acquista dove il cliente può acquistare a seconda del saldo disponibile con relativo importo rimanente dopo l'acquisto
    public void acquista(double importo) {
        if (importo > 0 && importo <= saldo) {
            saldo -= importo;
            System.out.println("Acquisto di " + importo + " effettuato con successo. Saldo attuale: " + saldo);
        } else if (importo <= 0) {
            System.out.println("L'importo dell'acquisto deve essere maggiore di zero.");
        } else {
            System.out.println("Saldo insufficiente per l'acquisto. Saldo attuale: " + saldo);
        }
    }

}
