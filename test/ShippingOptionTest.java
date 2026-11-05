import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShippingOptionTest {

    @Test
    public void testStandardOptionExists() {
        assertEquals(ShippingOption.STANDARD, ShippingOption.valueOf("STANDARD"));
    }

    @Test
    public void testNextDayOptionExists() {
        assertEquals(ShippingOption.NEXT_DAY, ShippingOption.valueOf("NEXT_DAY"));
    }

    @Test
    public void testShippingOptionCount() {
        assertEquals(2, ShippingOption.values().length);
    }

    @Test
    public void testShippingOptionOrder() {
        assertEquals(ShippingOption.STANDARD, ShippingOption.values()[0]);
        assertEquals(ShippingOption.NEXT_DAY, ShippingOption.values()[1]);
    }
}