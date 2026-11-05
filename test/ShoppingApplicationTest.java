
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingApplicationTest {

    @Test
    public void testGetCustomer() {
        Customer customer = new Customer("Layali", "IL");
        ShoppingApplication app = new ShoppingApplication(customer);

        assertSame(customer, app.getCustomer());
    }

    @Test
    public void testGetCart() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        assertNotNull(app.getCart());
    }

    @Test
    public void testSetShippingOption() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.setShippingOption(ShippingOption.NEXT_DAY);

        assertEquals(25.00, app.calculateShipping(), 0.001);
    }

    @Test
    public void testCalculateTaxIllinois() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertEquals(0.60, app.calculateTax(), 0.001);
    }

    @Test
    public void testCalculateTaxCalifornia() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "CA"));

        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertEquals(0.60, app.calculateTax(), 0.001);
    }

    @Test
    public void testCalculateTaxNewYork() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "NY"));

        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertEquals(0.60, app.calculateTax(), 0.001);
    }

    @Test
    public void testCalculateTaxOtherState() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "TX"));

        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertEquals(0.0, app.calculateTax(), 0.001);
    }

    @Test
    public void testCalculateTaxLowercaseState() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "il"));

        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertEquals(0.60, app.calculateTax(), 0.001);
    }

    @Test
    public void testStandardShipping() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertEquals(10.00, app.calculateShipping(), 0.001);
    }

    @Test
    public void testStandardShippingAt50Dollars() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 50.00, 1));

        assertEquals(10.00, app.calculateShipping(), 0.001);
    }

    @Test
    public void testFreeStandardShipping() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 51.00, 1));

        assertEquals(0.0, app.calculateShipping(), 0.001);
    }

    @Test
    public void testNextDayShipping() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.setShippingOption(ShippingOption.NEXT_DAY);
        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertEquals(25.00, app.calculateShipping(), 0.001);
    }

    @Test
    public void testCalculateTotal() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 100.00, 1));

        assertEquals(106.00, app.calculateTotal(), 0.001);
    }

    @Test
    public void testValidPurchaseAmountAtMinimum() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cookie", 1.00, 1));

        assertTrue(app.isValidPurchaseAmount());
    }

    @Test
    public void testValidPurchaseAmountAtMaximum() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 99999.99, 1));

        assertTrue(app.isValidPurchaseAmount());
    }

    @Test
    public void testInvalidPurchaseAmountBelowMinimum() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cookie", 0.99, 1));

        assertFalse(app.isValidPurchaseAmount());
    }

    @Test
    public void testInvalidPurchaseAmountAboveMaximum() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 100000.00, 1));

        assertFalse(app.isValidPurchaseAmount());
    }

    @Test
    public void testCheckoutWithEmptyCart() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        assertFalse(app.checkout());
    }

    @Test
    public void testCheckoutWithInvalidAmount() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cookie", 0.99, 1));

        assertFalse(app.checkout());
    }

    @Test
    public void testSuccessfulCheckout() {
        ShoppingApplication app =
                new ShoppingApplication(new Customer("Layali", "IL"));

        app.getCart().addItem(new Item("Cake", 10.00, 1));

        assertTrue(app.checkout());
    }
}
