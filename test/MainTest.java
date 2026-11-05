import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testProgramDisplaysWelcomeMessage() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("SANRIO SWEET CAFE"));
        assertTrue(output.contains("Welcome, Layali!"));
        assertTrue(output.contains("Thanks for visiting Sanrio Sweet Café!"));
    }

    @Test
    public void testInvalidMenuChoice() {
        String input = "Layali\nIL\n15\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Invalid choice. Please try again."));
    }

    @Test
    public void testBrowseProducts() {
        String input = "Layali\nIL\n1\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Hello Kitty Strawberry Latte"));
        assertTrue(output.contains("Pochacco Sticker Pack"));
    }

    @Test
    public void testCancelAddingProduct() {
        String input = "Layali\nIL\n2\n0\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Returning to the menu."));
    }

    private String runProgram(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)
            ));

            System.setOut(new PrintStream(output));

            Main.main(new String[]{});

            return output.toString();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

    }
    @Test
    public void testAddProductSuccessfully() {
        String input = "Layali\nIL\n2\n1\n2\n3\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Hello Kitty Strawberry Latte"));
    }

    @Test
    public void testInvalidProductNumber() {
        String input = "Layali\nIL\n2\n99\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Invalid product number."));
    }

    @Test
    public void testNonNumericProductNumber() {
        String input = "Layali\nIL\n2\nabc\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Please enter a whole number."));
    }

    @Test
    void testEditQuantitySuccessfully() {
        String input = "Layali\nIL\n2\n1\n3\n4\nHello Kitty Strawberry Latte\n5\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Quantity updated!"));
    }

    @Test
    void testEditQuantityInvalidNumber() {
        String input = "Layali\nIL\n2\n1\n3\n4\nHello Kitty Strawberry Latte\nabc\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Quantity must be a whole number."));
    }

    @Test
    void testEditQuantityLessThanOne() {
        String input = "Layali\nIL\n2\n1\n3\n4\nHello Kitty Strawberry Latte\n0\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Quantity must be at least 1."));
    }

    @Test
    void testRemoveItemSuccessfully() {
        String input = "Layali\nIL\n2\n1\n3\n5\nHello Kitty Strawberry Latte\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Item removed from cart."));
    }

    @Test
    void testRemoveItemNotFound() {
        String input = "Layali\nIL\n5\nUnknown Product\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Item not found."));
    }

    @Test
    void testShowTotal() {
        String input = "Layali\nIL\n2\n1\n3\n6\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("YOUR TOTAL"));
        assertTrue(output.contains("Subtotal:"));
        assertTrue(output.contains("Sales tax:"));
        assertTrue(output.contains("Shipping:"));
        assertTrue(output.contains("Total:"));
    }

    @Test
    void testChangeShippingToStandard() {
        String input = "Layali\nIL\n7\n1\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Standard shipping selected."));
    }

    @Test
    void testChangeShippingToNextDay() {
        String input = "Layali\nIL\n7\n2\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Next-day shipping selected."));
    }

    @Test
    void testChangeShippingInvalidOption() {
        String input = "Layali\nIL\n7\n3\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Invalid shipping option."));
    }

    @Test
    void testCheckoutSuccessfully() {
        String input = "Layali\nIL\n2\n1\n3\n8\n";

        String output = runProgram(input);

        assertTrue(output.contains("Transaction completed!"));
    }

    @Test
    void testCheckoutWithEmptyCart() {
        String input = "Layali\nIL\n8\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Checkout failed:"));
    }
    @Test
    void testAddProductQuantityLessThanOne() {
        String input = "Layali\nIL\n2\n1\n0\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Quantity must be at least 1."));
    }
    @Test
    void testAddProductQuantityNotANumber() {
        String input = "Layali\nIL\n2\n1\nabc\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Quantity must be a whole number."));
    }
}
