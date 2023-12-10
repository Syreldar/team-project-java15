package org.project.entities.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    public void testEquals() {
        Customer customer1 = new Customer("Enrico", "Drago", 24.52, "Viale dei Tigli 16", "enrico.drago3@gmail.com");
        Customer customer2 = new Customer("Enrico", "Drago", 24.52, "Viale dei Tigli 16", "enrico.drago3@gmail.com");

        assertEquals(customer1, customer2);

        Customer customer3 = new Customer("Jane", "Doe", 1500.0, "Via Giglio 65", "janedoe@gmail.com");
        Customer customer4 = new Customer("John", "Smith", 1000.0,"Via Salice 95", "johnsmith@alice.com");

        assertNotEquals(customer1, customer3);
        assertNotEquals(customer1, customer4);
    }
}

