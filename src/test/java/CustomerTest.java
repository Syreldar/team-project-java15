import org.junit.Before;
import org.junit.Test;
import org.project.models.Customer;

import static org.junit.Assert.*;

public class CustomerTest {
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("John", "Doe", 100.0);
    }

    @Test
    public void testGetFullName() {
        String fullName = customer.getFullName();
        assertEquals("John Doe", fullName);
    }

    @Test
    public void testGetBalance() {
        double balance = customer.getBalance();
        assertEquals(100.0, balance, 0.001);
    }

    @Test
    public void testAddToBalance() {
        customer.setBalance(customer.getBalance() + 50.0);
        double newBalance = customer.getBalance();
        assertEquals(150.0, newBalance, 0.001);
    }

    @Test
    public void testWithdrawFromBalance() {
        boolean success = customer.subtractFromBalance(30.0);
        assertTrue(success);
        double newBalance = customer.getBalance();
        assertEquals(70.0, newBalance, 0.001);
    }

    @Test
    public void testWithdrawFromBalanceInsufficientFunds() {
        boolean success = customer.subtractFromBalance(200.0);
        assertFalse(success);
        double newBalance = customer.getBalance();
        assertEquals(100.0, newBalance, 0.001);
    }
}