package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents the total amount of a sale including taxes.
 *
 * This class calculates and maintains the total amount of a sale, along with
 * the total tax amount.
 */
public class Total {

    private Amount total;
    private Amount totalTax;

    /**
     * Creates a new instance of the Total class, initialized with zero values.
     */
    public Total() {
        this.total = new Amount(0);
        this.totalTax = new Amount(0);
    }

    /**
     * Retrieves the total amount of the sale.
     *
     * @return The total amount.
     */
    public Amount getTotal() {
        return total;
    }

    /**
     * Retrieves the total tax amount of the sale.
     *
     * @return The total tax amount.
     */
    public Amount getTotalTax() {
        return totalTax;
    }

    /**
     * Calculates and returns the total amount including taxes.
     *
     * @return The total amount with taxes included.
     */
    public Amount getTotalWithTax() {
        return total.plus(totalTax);
    }

    /**
     * Updates the total and total tax based on the provided item.
     *
     * @param item The item to be added to the sale, containing price and tax
     * information.
     */
    public void updateTotal(Item item) {
        if (item == null) {
            return;
        }
        Amount amountOfItems = item.getQuantity();
        Amount itemPrice = item.getItemDescription().getPrice();
        Amount itemTax = item.getItemDescription().getTax();

        this.totalTax = this.totalTax.plus(amountOfItems.multiply(itemTax));
        this.total = this.total.plus(amountOfItems.multiply(itemPrice));
    }
}
