package org.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private double balance = 0.0;

    public Customer() {}

    public Customer(String firstName, String lastName, double balance) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("firstName and lastName arguments cannot be null");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Customer customer = (Customer) o;
        return Double.compare(customer.balance, balance) == 0 &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, balance);
    }

    @Override
    public String toString() {
        return String.format("Customer [FirstName: %s, LastName: %s, Balance: %.2f]",
                this.firstName, this.lastName, this.balance);
    }
}
