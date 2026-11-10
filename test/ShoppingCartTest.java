
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    public void testNewCartIsEmpty() {
        ShoppingCart cart = new ShoppingCart();

        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.getSubtotal(), 0.001);
    }

    @Test
    public void testAddItem() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Strawberry Cake", 5.99, 2);

        cart.addItem(item);

        assertFalse(cart.isEmpty());
        assertEquals(2, cart.getItemCount());
    }

    @Test
    public void testAddDuplicateItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Strawberry Cake", 5.99, 2));
        cart.addItem(new Item("Strawberry Cake", 5.99, 3));

        assertEquals(5, cart.getItemCount());
    }

    @Test
    public void testAddItemWithDifferentCapitalization() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Strawberry Cake", 5.99, 2));
        cart.addItem(new Item("strawberry cake", 5.99, 3));

        assertEquals(5, cart.getItemCount());
    }

    @Test
    public void testGetItemCount() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.addItem(new Item("Cookie", 2.00, 3));

        assertEquals(5, cart.getItemCount());
    }

    @Test
    public void testGetSubtotal() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.addItem(new Item("Cookie", 2.00, 3));

        assertEquals(16.00, cart.getSubtotal(), 0.001);
    }

    @Test
    public void testUpdateQuantity() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.updateQuantity("Cake", 5);

        assertEquals(5, cart.getItemCount());
        assertEquals(25.00, cart.getSubtotal(), 0.001);
    }

    @Test
    public void testUpdateQuantityWithDifferentCapitalization() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.updateQuantity("cake", 4);

        assertEquals(4, cart.getItemCount());
    }

    @Test
    public void testUpdateQuantityWithInvalidQuantity() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.updateQuantity("Cake", 0);

        assertEquals(2, cart.getItemCount());
    }

    @Test
    public void testUpdateQuantityForMissingItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.updateQuantity("Cookie", 4);

        assertEquals(2, cart.getItemCount());
    }

    @Test
    public void testRemoveItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.removeItem("Cake");

        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItemCount());
    }

    @Test
    public void testRemoveItemWithDifferentCapitalization() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.removeItem("cake");

        assertTrue(cart.isEmpty());
    }

    @Test
    public void testRemoveMissingItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.removeItem("Cookie");

        assertEquals(2, cart.getItemCount());
    }

    @Test
    public void testViewEmptyCart() {
        ShoppingCart cart = new ShoppingCart();

        assertDoesNotThrow(() -> cart.viewCart());
    }

    @Test
    public void testViewCartWithItems() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Cake", 5.00, 2));
        cart.addItem(new Item("Cookie", 2.00, 3));

        assertDoesNotThrow(() -> cart.viewCart());
    }
    @Test
    void testAddItemPrintsConfirmationMessage() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Hello Kitty Latte", 5.50, 1);

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.addItem(item);
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Item added to cart!"));
    }
    @Test
    void testAddItemPrintsCurrentItemCount() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Hello Kitty Latte", 5.50, 2);

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.addItem(item);
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Current number of items: 2"));
    }
    @Test
    void testAddNewItemPrintsConfirmationMessage() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Kuromi Frappe", 6.00, 1);

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.addItem(item);
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Item added to cart!"));
    }
    @Test
    void testAddNewItemPrintsCurrentItemCount() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Kuromi Frappe", 6.00, 3);

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.addItem(item);
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Current number of items: 3"));
    }
    @Test
    void testViewEmptyCartPrintsMessage() {
        ShoppingCart cart = new ShoppingCart();

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.viewCart();
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Your shopping cart is empty."));
    }
    @Test
    void testViewCartPrintsHeading() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Hello Kitty Latte", 5.50, 1));

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.viewCart();
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("===== YOUR SANRIO CART ====="));
    }
    @Test
    void testUpdateQuantityWithQuantityOfOne() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Hello Kitty Latte", 5.50, 2);

        cart.addItem(item);
        cart.updateQuantity("Hello Kitty Latte", 1);

        assertEquals(1, item.getQuantity());
        assertEquals(1, cart.getItemCount());
        assertEquals(5.50, cart.getSubtotal(), 0.001);
    }
    @Test
    void testUpdateQuantityPrintsWarningForInvalidQuantity() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Hello Kitty Latte", 5.50, 2);

        cart.addItem(item);

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.updateQuantity("Hello Kitty Latte", 0);
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Quantity must be at least 1."));
        assertEquals(2, item.getQuantity());
    }
    @Test
    void testUpdateQuantityPrintsMessageWhenItemNotFound() {
        ShoppingCart cart = new ShoppingCart();

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));
            cart.updateQuantity("Missing item", 2);
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Item not found."));
    }
    @Test
    void testAddingDuplicateItemPrintsConfirmationMessage() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Hello Kitty Latte", 5.50, 1));

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));

            cart.addItem(new Item("Hello Kitty Latte", 5.50, 2));
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Item added to cart!"));
    }
    @Test
    void testAddingDuplicateItemPrintsUpdatedItemCount() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Item("Hello Kitty Latte", 5.50, 1));

        java.io.PrintStream originalOut = System.out;
        java.io.ByteArrayOutputStream output =
                new java.io.ByteArrayOutputStream();

        try {
            System.setOut(new java.io.PrintStream(output));

            cart.addItem(new Item("Hello Kitty Latte", 5.50, 2));
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(output.toString().contains("Current number of items: 3"));
    }
}