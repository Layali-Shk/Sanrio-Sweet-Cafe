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

    @Test
    void testProgramDisplaysOpeningBanner() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("================================"));
    }

    @Test
    void testProgramDisplaysCafeSubtitle() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Drinks, Sweets & Cute Things"));
    }

    @Test
    void testProgramDisplaysClosingWelcomeBanner() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("================================"));
    }

    @Test
    void testProgramDisplaysStatePrompt() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Enter your state (2-letter code):"));
    }

    @Test
    void testProgramDisplaysClosingWelcomeMessage() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("We hope you find something you love!"));
    }

    @Test
    void testProgramDisplaysMainMenuHeading() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("MAIN MENU"));
    }

    @Test
    void testMainMenuDisplaysBrowseProductsOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("1. Browse products"));
    }

    @Test
    void testMainMenuDisplaysAddProductOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("2. Add product to cart"));
    }

    @Test
    void testMainMenuDisplaysViewCartOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("3. View shopping cart"));
    }

    @Test
    void testMainMenuDisplaysEditQuantityOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("4. Edit item quantity"));
    }

    @Test
    void testMainMenuDisplaysRemoveItemOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("5. Remove item from cart"));
    }

    @Test
    void testMainMenuDisplaysCurrentTotalOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("6. View current total"));
    }
    @Test
    void testMainMenuDisplaysChangeShippingOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("7. Change shipping option"));
    }
    @Test
    void testMainMenuDisplaysCheckoutOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("8. Checkout"));
    }
    @Test
    void testMainMenuDisplaysExitOption() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("9. Exit"));
    }
    @Test
    void testMainMenuDisplaysChooseOptionPrompt() {
        String input = "Layali\nIL\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Choose an option:"));
    }
    @Test
    void testAddProductDisplaysProductNumberPrompt() {
        String input = "Layali\nIL\n2\n0\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Enter product number (0 to cancel):"));
    }
    @Test
    void testAddProductDisplaysQuantityPrompt() {
        String input = "Layali\nIL\n2\n1\n1\n9\n";

        String output = runProgram(input);

        assertTrue(output.contains("Enter quantity:"));
    }
    @Test
    void testAddProductRejectsQuantityBelowOne() {
        String input = "Layali\nIL\n2\n1\n0\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Quantity must be at least 1."));
    }
    @Test
    void testAddProductSetsQuantity() {
        String input = "Layali\nIL\n2\n1\n3\n3\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Hello Kitty Strawberry Latte"));
        assertTrue(output.contains("Quantity: 3"));
        assertTrue(output.contains("$16.50"));
    }
    @Test
    void testEditQuantityDisplaysProductNamePrompt() {
        String input = "Layali\nIL\n4\nHello Kitty Latte\n2\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Enter the product name to edit:"));
    }
    @Test
    void testEditQuantityDisplaysNewQuantityPrompt() {
        String input = "Layali\nIL\n4\nHello Kitty Latte\n2\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Enter the new quantity:"));
    }
    @Test
    void testEditQuantityRejectsQuantityBelowOne() {
        String input = "Layali\nIL\n4\nHello Kitty Latte\n0\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Quantity must be at least 1."));
    }
    @Test
    void testRemoveItemDisplaysProductNamePrompt() {
        String input = "Layali\nIL\n5\nHello Kitty Latte\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Enter the product name to remove:"));
    }
    @Test
    void testShippingDisplaysOptionsHeading() {
        String input = "Layali\nIL\n7\n1\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("SHIPPING OPTIONS"));
    }
    @Test
    void testShippingDisplaysStandardOption() {
        String input = "Layali\nIL\n7\n1\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("1. STANDARD - $10, free over $50"));
    }
    @Test
    void testShippingDisplaysNextDayOption() {
        String input = "Layali\nIL\n7\n1\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("2. NEXT_DAY - $25"));
    }
    @Test
    void testShippingDisplaysChoosePrompt() {
        String input = "Layali\nIL\n7\n1\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Choose shipping:"));
    }
    @Test
    void testStandardShippingIsSelected() {
        String input = "Layali\nIL\n7\n1\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Standard shipping selected."));
    }
    @Test
    void testNextDayShippingIsSelected() {
        String input = "Layali\nIL\n7\n2\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Next-day shipping selected."));
    }
    @Test
    void testWelcomeDisplaysBothSeparators() {
        String input = "Layali\nIL\n9\n";
        String output = runProgram(input);

        int count = output.split("================================", -1).length - 1;

        assertTrue(count >= 2);
    }
    @Test
    void testAddProductAcceptsQuantityOne() {
        String input = "Layali\nIL\n2\n1\n1\n3\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Enter quantity:"));
        assertTrue(output.contains("Hello Kitty"));
    }
    @Test
    void testEditQuantityAcceptsQuantityOne() {
        String input = "Layali\nIL\n2\n1\n2\n4\nHello Kitty Strawberry Latte\n1\n9\n";
        String output = runProgram(input);

        assertFalse(output.contains("Quantity must be at least 1."));
    }
    @Test
    void testProgramDisplaysNamePrompt() {
        String input = "Layali\nIL\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Enter your name:"));
    }
    @Test
    void testStandardShippingSelectionChangesShippingCost() {
        String input = "Layali\nIL\n7\n2\n7\n1\n6\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Standard shipping selected."));
        assertTrue(output.contains("$10.00"));
    }

    @Test
    void testNextDayShippingSelectionChangesShippingCost() {
        String input = "Layali\nIL\n7\n1\n7\n2\n6\n9\n";
        String output = runProgram(input);

        assertTrue(output.contains("Next-day shipping selected."));
        assertTrue(output.contains("$25.00"));
    }

}
