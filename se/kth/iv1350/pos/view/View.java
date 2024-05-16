package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.util.Amount;

/**
 * This acts as a placeholder for the real view - makes a sample execution
 */
public class View {

    private final Controller controller;

    /**
     * Creates a new instance
     *
     * @param controller Controller that is used for all operations
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Simulates a user input that generates calls to all system ops.
     */
    public void sampleExecution() {
        System.out.println("START OF NEW SALE\n");
        controller.startNewSale();
        System.out.println("Cashier enter items : \n");
        Item item = controller.registerItem("Kyckling", new Amount(1));
        printItem(item);
        item = controller.registerItem("Potatis", new Amount(5));
        printItem(item);
        item = controller.registerItem("Ärtor", new Amount(100));
        printItem(item);
        System.out.println("\nCashier displays the total with taxes : ");
        Amount totalWithTax = controller.displayTotalWithTax();
        System.out.println("Total with taxes: " + totalWithTax);
        System.out.println("\nCashier enters the paid amount. \n");
        Amount change = controller.pay(new Amount(2000));
        System.out.println("Paid amount: 2000, Change: " + change);
    }

    private void printItem(Item item) {
        if (item != null) {
            System.out.println("Item name: " + item.getItemDescription().getItemName()
                    + "     Price: " + item.getItemDescription().getPrice()
                    + "     Quantity: " + item.getQuantity()
                    + "     Running total: " + controller.displayTotal());
        } else {
            System.out.println("Running total: " + controller.displayTotal());
        }
    }
}
