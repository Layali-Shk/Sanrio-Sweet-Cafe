
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();

        System.out.println(
                TerminalColors.PINK + "\n================================"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.PINK + "      SANRIO SWEET CAFE"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.BLUE + "   Drinks, Sweets & Cute Things"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.PINK + "================================"
                        + TerminalColors.RESET
        );

        System.out.print(
                TerminalColors.BLUE + "Enter your name: "
                        + TerminalColors.RESET
        );
        String name = scanner.nextLine().trim();

        System.out.print(
                TerminalColors.BLUE + "Enter your state (2-letter code): "
                        + TerminalColors.RESET
        );
        String state = scanner.nextLine().trim().toUpperCase();

        Customer customer = new Customer(name, state);
        ShoppingApplication app = new ShoppingApplication(customer);

        System.out.println(
                TerminalColors.LAVENDER + "\nWelcome, " + customer.getName() + "!"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.LAVENDER + "We hope you find something you love!"
                        + TerminalColors.RESET
        );

        boolean running = true;

        while (running) {
            System.out.println(
                    TerminalColors.PINK + "\n========== MAIN MENU =========="
                            + TerminalColors.RESET
            );

            System.out.println(TerminalColors.LAVENDER + "1. Browse products" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "2. Add product to cart" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "3. View shopping cart" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "4. Edit item quantity" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "5. Remove item from cart" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "6. View current total" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "7. Change shipping option" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "8. Checkout" + TerminalColors.RESET);
            System.out.println(TerminalColors.LAVENDER + "9. Exit" + TerminalColors.RESET);

            System.out.print(
                    TerminalColors.BLUE + "Choose an option: "
                            + TerminalColors.RESET
            );

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    catalog.displayProducts();
                    break;
                case "2":
                    addProduct(catalog, app);
                    break;
                case "3":
                    app.getCart().viewCart();
                    break;
                case "4":
                    editQuantity(app);
                    break;
                case "5":
                    removeItem(app);
                    break;
                case "6":
                    showTotal(app);
                    break;
                case "7":
                    changeShipping(app);
                    break;
                case "8":
                    if (app.checkout()) {
                        running = false;
                    }
                    break;
                case "9":
                    running = false;
                    System.out.println(
                            TerminalColors.LAVENDER + "Thanks for visiting Sanrio Sweet Café!"
                                    + TerminalColors.RESET
                    );
                    break;
                default:
                    System.out.println(
                            TerminalColors.PURPLE + "Invalid choice. Please try again."
                                    + TerminalColors.RESET
                    );
            }
        }

        scanner.close();
    }

    private static void addProduct(ProductCatalog catalog, ShoppingApplication app) {

        System.out.print(
                TerminalColors.BLUE + "\nEnter product number (0 to cancel): "
                        + TerminalColors.RESET
        );

        int productNumber;

        try {
            productNumber = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(
                    TerminalColors.PURPLE + "Please enter a whole number."
                            + TerminalColors.RESET
            );
            return;
        }

        if (productNumber == 0) {
            System.out.println(
                    TerminalColors.LAVENDER + "Returning to the menu."
                            + TerminalColors.RESET
            );
            return;
        }

        Item product = catalog.getProduct(productNumber);

        if (product == null) {
            System.out.println(
                    TerminalColors.PURPLE + "Invalid product number."
                            + TerminalColors.RESET
            );
            return;
        }

        System.out.print(
                TerminalColors.BLUE + "Enter quantity: "
                        + TerminalColors.RESET
        );

        int quantity;

        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity < 1) {
                System.out.println(
                        TerminalColors.PURPLE + "Quantity must be at least 1."
                                + TerminalColors.RESET
                );
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    TerminalColors.PURPLE + "Quantity must be a whole number."
                            + TerminalColors.RESET
            );
            return;
        }

        product.setQuantity(quantity);
        app.getCart().addItem(product);
    }

    private static void editQuantity(ShoppingApplication app) {
        System.out.print(
                TerminalColors.BLUE + "Enter the product name to edit: "
                        + TerminalColors.RESET
        );
        String name = scanner.nextLine().trim();

        System.out.print(
                TerminalColors.BLUE + "Enter the new quantity: "
                        + TerminalColors.RESET
        );

        int quantity;

        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity < 1) {
                System.out.println(
                        TerminalColors.PURPLE + "Quantity must be at least 1."
                                + TerminalColors.RESET
                );
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    TerminalColors.PURPLE + "Quantity must be a whole number."
                            + TerminalColors.RESET
            );
            return;
        }

        app.getCart().updateQuantity(name, quantity);
    }

    private static void removeItem(ShoppingApplication app) {
        System.out.print(
                TerminalColors.BLUE + "Enter the product name to remove: "
                        + TerminalColors.RESET
        );
        String name = scanner.nextLine().trim();

        app.getCart().removeItem(name);
    }

    private static void showTotal(ShoppingApplication app) {
        System.out.println(
                TerminalColors.PINK + "\n========== YOUR TOTAL =========="
                        + TerminalColors.RESET
        );

        System.out.printf(
                TerminalColors.LAVENDER + "Subtotal: "
                        + TerminalColors.YELLOW + "$%.2f%n"
                        + TerminalColors.RESET,
                app.getCart().getSubtotal()
        );

        System.out.printf(
                TerminalColors.LAVENDER + "Sales tax: "
                        + TerminalColors.YELLOW + "$%.2f%n"
                        + TerminalColors.RESET,
                app.calculateTax()
        );

        System.out.printf(
                TerminalColors.LAVENDER + "Shipping: "
                        + TerminalColors.YELLOW + "$%.2f%n"
                        + TerminalColors.RESET,
                app.calculateShipping()
        );

        System.out.printf(
                TerminalColors.BLUE + "Total: $%.2f%n"
                        + TerminalColors.RESET,
                app.calculateTotal()
        );
    }

    private static void changeShipping(ShoppingApplication app) {
        System.out.println(
                TerminalColors.PINK + "\n====== SHIPPING OPTIONS ======"
                        + TerminalColors.RESET
        );

        System.out.println(
                TerminalColors.LAVENDER + "1. STANDARD - $10, free over $50"
                        + TerminalColors.RESET
        );
        System.out.println(
                TerminalColors.LAVENDER + "2. NEXT_DAY - $25"
                        + TerminalColors.RESET
        );

        System.out.print(
                TerminalColors.BLUE + "Choose shipping: "
                        + TerminalColors.RESET
        );

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                app.setShippingOption(ShippingOption.STANDARD);
                System.out.println(
                        TerminalColors.LAVENDER + "Standard shipping selected."
                                + TerminalColors.RESET
                );
                break;
            case "2":
                app.setShippingOption(ShippingOption.NEXT_DAY);
                System.out.println(
                        TerminalColors.LAVENDER + "Next-day shipping selected."
                                + TerminalColors.RESET
                );
                break;
            default:
                System.out.println(
                        TerminalColors.PURPLE + "Invalid shipping option."
                                + TerminalColors.RESET
                );
        }
    }
}
