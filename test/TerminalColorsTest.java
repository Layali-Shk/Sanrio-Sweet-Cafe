import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TerminalColorsTest {

    @Test
    void testPinkColor() {
        assertEquals("\u001B[38;2;255;182;217m", TerminalColors.PINK);
    }

    @Test
    void testBlueColor() {
        assertEquals("\u001B[38;2;174;235;255m", TerminalColors.BLUE);
    }

    @Test
    void testLavenderColor() {
        assertEquals("\u001B[38;2;220;203;255m", TerminalColors.LAVENDER);
    }

    @Test
    void testYellowColor() {
        assertEquals("\u001B[38;2;255;240;168m", TerminalColors.YELLOW);
    }

    @Test
    void testPurpleColor() {
        assertEquals("\u001B[38;2;220;194;255m", TerminalColors.PURPLE);
    }

    @Test
    void testResetColor() {
        assertEquals("\u001B[0m", TerminalColors.RESET);
    }
}