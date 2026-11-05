
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testGetName() {
        Item item = new Item("Strawberry Cake", 5.99, 2);

        assertEquals("Strawberry Cake", item.getName());
    }

    @Test
    public void testGetPrice() {
        Item item = new Item("Strawberry Cake", 5.99, 2);

        assertEquals(5.99, item.getPrice(), 0.001);
    }

    @Test
    public void testGetQuantity() {
        Item item = new Item("Strawberry Cake", 5.99, 2);

        assertEquals(2, item.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        Item item = new Item("Strawberry Cake", 5.99, 2);

        item.setQuantity(5);

        assertEquals(5, item.getQuantity());
    }

    @Test
    public void testGetTotalPrice() {
        Item item = new Item("Strawberry Cake", 5.99, 2);

        assertEquals(11.98, item.getTotalPrice(), 0.001);
    }
}
