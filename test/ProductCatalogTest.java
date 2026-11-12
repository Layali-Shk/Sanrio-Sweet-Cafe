
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ProductCatalogTest {

    @Test
    public void testProductCount() {
        ProductCatalog catalog = new ProductCatalog();

        assertEquals(15, catalog.getProductCount());
    }

    @Test
    public void testGetFirstProduct() {
        ProductCatalog catalog = new ProductCatalog();

        Item product = catalog.getProduct(1);

        assertNotNull(product);
        assertEquals("Hello Kitty Strawberry Latte", product.getName());
        assertEquals(5.50, product.getPrice(), 0.001);
    }

    @Test
    public void testGetLastProduct() {
        ProductCatalog catalog = new ProductCatalog();

        Item product = catalog.getProduct(15);

        assertNotNull(product);
        assertEquals("Pochacco Sticker Pack", product.getName());
        assertEquals(3.00, product.getPrice(), 0.001);
    }

    @Test
    public void testGetMiddleProduct() {
        ProductCatalog catalog = new ProductCatalog();

        Item product = catalog.getProduct(8);

        assertNotNull(product);
        assertEquals("Cinnamoroll Cloud Cake", product.getName());
        assertEquals(5.00, product.getPrice(), 0.001);
    }

    @Test
    public void testGetProductWithInvalidNumberZero() {
        ProductCatalog catalog = new ProductCatalog();

        assertNull(catalog.getProduct(0));
    }

    @Test
    public void testGetProductWithNegativeNumber() {
        ProductCatalog catalog = new ProductCatalog();

        assertNull(catalog.getProduct(-1));
    }

    @Test
    public void testGetProductWithNumberTooLarge() {
        ProductCatalog catalog = new ProductCatalog();

        assertNull(catalog.getProduct(16));
    }

    @Test
    public void testReturnedProductHasQuantityOne() {
        ProductCatalog catalog = new ProductCatalog();

        Item product = catalog.getProduct(1);

        assertEquals(1, product.getQuantity());
    }


    @Test
    public void testDisplayProducts() {
        ProductCatalog catalog = new ProductCatalog();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;

        try {
            System.setOut(new PrintStream(output));
            catalog.displayProducts();
        } finally {
            System.setOut(originalOutput);
        }

        String result = output.toString();

        // Check catalog headings
        assertTrue(result.contains("SANRIO SWEET CAFE"));
        assertTrue(result.contains("SHOP CATALOG"));
        assertTrue(result.contains("CHARACTER DRINKS"));
        assertTrue(result.contains("SANRIO SWEETS"));
        assertTrue(result.contains("CHARACTER MERCHANDISE"));

        // Check all 15 products are displayed
        assertTrue(result.contains("Hello Kitty Strawberry Latte"));
        assertTrue(result.contains("Kuromi Purple Frappe"));
        assertTrue(result.contains("Cinnamoroll Cloud Latte"));
        assertTrue(result.contains("Pompompurin Caramel Shake"));
        assertTrue(result.contains("Keroppi Melon Soda"));

        assertTrue(result.contains("My Melody Pink Cupcake"));
        assertTrue(result.contains("Hello Kitty Bow Cookies"));
        assertTrue(result.contains("Cinnamoroll Cloud Cake"));
        assertTrue(result.contains("Pompompurin Custard Pudding"));
        assertTrue(result.contains("Kuromi Chocolate Donut"));

        assertTrue(result.contains("Hello Kitty Mug"));
        assertTrue(result.contains("Kuromi Plushie"));
        assertTrue(result.contains("Cinnamoroll Keychain"));
        assertTrue(result.contains("My Melody Tote Bag"));
        assertTrue(result.contains("Pochacco Sticker Pack"));

        // Check product numbering
        assertTrue(result.contains("1. Hello Kitty Strawberry Latte"));
        assertTrue(result.contains("15. Pochacco Sticker Pack"));

        // Check the catalog separators appear three times
        assertEquals(
                3,
                result.split("==================================", -1).length - 1
        );
    }


    @Test
    public void testReturnedProductIsIndependentCopy() {
        ProductCatalog catalog = new ProductCatalog();

        Item product = catalog.getProduct(1);

        product.setQuantity(5);

        assertEquals(1, catalog.getProduct(1).getQuantity());
    }
}
