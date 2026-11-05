import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testGetName() {
        Customer customer = new Customer("Layali", "IL");

        assertEquals("Layali", customer.getName());
    }

    @Test
    public void testGetState() {
        Customer customer = new Customer("Layali", "IL");

        assertEquals("IL", customer.getState());
    }

    @Test
    public void testDifferentCustomerDetails() {
        Customer customer = new Customer("Alex", "CA");

        assertEquals("Alex", customer.getName());
        assertEquals("CA", customer.getState());
    }

    @Test
    public void testEmptyCustomerDetails() {
        Customer customer = new Customer("", "");

        assertEquals("", customer.getName());
        assertEquals("", customer.getState());
    }
}