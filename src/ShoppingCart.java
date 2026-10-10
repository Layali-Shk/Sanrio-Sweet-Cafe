
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        for (Item existing : items) {
            if (existing.getName().equalsIgnoreCase(item.getName())) {
                existing.setQuantity(
                        existing.getQuantity() + item.getQuantity()
                );

                System.out.println(
                        TerminalColors.LAVENDER + "Item added to cart!"
                                + TerminalColors.RESET
                );
                System.out.println(
                        TerminalColors.BLUE + "Current number of items: "
                                + getItemCount() + TerminalColors.RESET
                );
                return;
            }
        }

        items.add(item);

        System.out.println(
                TerminalColors.LAVENDER + "Item added to cart!"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.BLUE + "Current number of items: "
                        + getItemCount() + TerminalColors.RESET
        );
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println(
                    TerminalColors.PURPLE + "Your shopping cart is empty."
                            + TerminalColors.RESET
            );
            return;
        }

        System.out.println(
                TerminalColors.PINK + "\n===== YOUR SANRIO CART ====="
                        + TerminalColors.RESET
        );

        for (Item item : items) {
            System.out.printf(
                    TerminalColors.LAVENDER
                            + "%s | Price: "
                            + TerminalColors.YELLOW + "$%.2f"
                            + TerminalColors.LAVENDER + " | Quantity: %d | Total: "
                            + TerminalColors.YELLOW + "$%.2f%n"
                            + TerminalColors.RESET,
                    item.getName(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
        }

        System.out.printf(
                TerminalColors.LAVENDER + "Subtotal: "
                        + TerminalColors.YELLOW + "$%.2f%n"
                        + TerminalColors.RESET,
                getSubtotal()
        );
    }

    public void updateQuantity(String itemName, int quantity) {
        if (quantity < 1) {
            System.out.println(
                    TerminalColors.PURPLE + "Quantity must be at least 1."
                            + TerminalColors.RESET
            );
            return;
        }

        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setQuantity(quantity);

                System.out.println(
                        TerminalColors.LAVENDER + "Quantity updated!"
                                + TerminalColors.RESET
                );
                return;
            }
        }

        System.out.println(
                TerminalColors.PURPLE + "Item not found."
                        + TerminalColors.RESET
        );
    }

    public void removeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                items.remove(i);

                System.out.println(
                        TerminalColors.LAVENDER + "Item removed from cart."
                                + TerminalColors.RESET
                );
                return;
            }
        }

        System.out.println(
                TerminalColors.PURPLE + "Item not found."
                        + TerminalColors.RESET
        );
    }

    public int getItemCount() {
        int count = 0;

        for (Item item : items) {
            count += item.getQuantity();
        }

        return count;
    }

    public double getSubtotal() {
        double subtotal = 0;

        for (Item item : items) {
            subtotal += item.getTotalPrice();
        }

        return subtotal;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
