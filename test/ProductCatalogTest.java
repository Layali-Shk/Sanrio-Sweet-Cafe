
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

        assertTrue(result.contains("SANRIO SWEET CAFE"));
        assertTrue(result.contains("CHARACTER DRINKS"));
        assertTrue(result.contains("SANRIO SWEETS"));
        assertTrue(result.contains("CHARACTER MERCHANDISE"));
        assertTrue(result.contains("Hello Kitty Strawberry Latte"));
        assertTrue(result.contains("Pochacco Sticker Pack"));
    }

    @Test
    public void testReturnedProductIsIndependentCopy() {
        ProductCatalog catalog = new ProductCatalog();

        Item product = catalog.getProduct(1);

        product.setQuantity(5);

        assertEquals(1, catalog.getProduct(1).getQuantity());
    }
}
