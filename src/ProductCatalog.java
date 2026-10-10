
import java.util.ArrayList;

public class ProductCatalog {
    private ArrayList<Item> products;

    public ProductCatalog() {
        products = new ArrayList<>();

        // Character Drinks
        products.add(new Item("Hello Kitty Strawberry Latte", 5.50, 1));
        products.add(new Item("Kuromi Purple Frappe", 6.00, 1));
        products.add(new Item("Cinnamoroll Cloud Latte", 5.75, 1));
        products.add(new Item("Pompompurin Caramel Shake", 5.50, 1));
        products.add(new Item("Keroppi Melon Soda", 4.50, 1));

        // Sanrio Sweets
        products.add(new Item("My Melody Pink Cupcake", 4.50, 1));
        products.add(new Item("Hello Kitty Bow Cookies", 4.00, 1));
        products.add(new Item("Cinnamoroll Cloud Cake", 5.00, 1));
        products.add(new Item("Pompompurin Custard Pudding", 3.50, 1));
        products.add(new Item("Kuromi Chocolate Donut", 4.25, 1));

        // Character Merchandise
        products.add(new Item("Hello Kitty Mug", 12.00, 1));
        products.add(new Item("Kuromi Plushie", 22.00, 1));
        products.add(new Item("Cinnamoroll Keychain", 6.50, 1));
        products.add(new Item("My Melody Tote Bag", 15.00, 1));
        products.add(new Item("Pochacco Sticker Pack", 3.00, 1));
    }

    public void displayProducts() {
        System.out.println(
                TerminalColors.PINK + "\n=================================="
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.PINK + "       SANRIO SWEET CAFE"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.BLUE + "         SHOP CATALOG"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.PINK + "=================================="
                        + TerminalColors.RESET
        );

        System.out.println(
                TerminalColors.PINK + "\n-------- CHARACTER DRINKS --------"
                        + TerminalColors.RESET
        );
        displayCategory(1, 5);

        System.out.println(
                TerminalColors.PINK + "\n---------- SANRIO SWEETS ----------"
                        + TerminalColors.RESET
        );
        displayCategory(6, 10);

        System.out.println(
                TerminalColors.PINK + "\n------ CHARACTER MERCHANDISE ------"
                        + TerminalColors.RESET
        );
        displayCategory(11, 15);

        System.out.println(
                TerminalColors.PINK + "\n=================================="
                        + TerminalColors.RESET
        );
    }

    private void displayCategory(int start, int end) {
        for (int i = start - 1; i < end; i++) {
            Item product = products.get(i);

            System.out.printf(
                    TerminalColors.LAVENDER + "%2d. %-32s "
                            + TerminalColors.YELLOW + "$%.2f%n"
                            + TerminalColors.RESET,
                    i + 1,
                    product.getName(),
                    product.getPrice()
            );
        }
    }

    public Item getProduct(int productNumber) {
        if (productNumber < 1 || productNumber > products.size()) {
            return null;
        }

        Item product = products.get(productNumber - 1);
        return new Item(product.getName(), product.getPrice(), 1);
    }

    public int getProductCount() {
        return products.size();
    }
}
