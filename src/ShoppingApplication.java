
public class ShoppingApplication {
    private Customer customer;
    private ShoppingCart cart;
    private ShippingOption shippingOption;

    public ShoppingApplication(Customer customer) {
        this.customer = customer;
        this.cart = new ShoppingCart();
        this.shippingOption = ShippingOption.STANDARD;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setShippingOption(ShippingOption shippingOption) {
        this.shippingOption = shippingOption;
    }

    public double calculateTax() {
        String state = customer.getState().toUpperCase();

        if (state.equals("IL") || state.equals("CA") || state.equals("NY")) {
            return cart.getSubtotal() * 0.06;
        }

        return 0.0;
    }

    public double calculateShipping() {
        double subtotal = cart.getSubtotal();

        if (shippingOption == ShippingOption.STANDARD) {
            if (subtotal > 50.00) {
                return 0.0;
            }
            return 10.00;
        }

        return 25.00;
    }

    public double calculateTotal() {
        return cart.getSubtotal() + calculateTax() + calculateShipping();
    }

    public boolean isValidPurchaseAmount() {
        double subtotal = cart.getSubtotal();
        return subtotal >= 1.00 && subtotal <= 99999.99;
    }

    public boolean checkout() {
        if (cart.isEmpty() || !isValidPurchaseAmount()) {
            System.out.println(
                    TerminalColors.PURPLE
                            + "Checkout failed: purchase amount must be between $1.00 and $99,999.99."
                            + TerminalColors.RESET
            );
            return false;
        }

        System.out.println(
                TerminalColors.PINK + "transaction completed"
                        + TerminalColors.RESET
        );
        return true;
    }
}
