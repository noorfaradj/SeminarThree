package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.util.Amount;

/**
 * Payment for specific sale.
 */
public class Payment {

    private final Amount paidAmount;
    private final Total total;

    /**
     * New instance
     *
     * @param paidAmount amount paid by customer
     * @param total total that has to be paid
     */
    public Payment(Amount paidAmount, Total total) {
        this.paidAmount = paidAmount;
        this.total = total;
    }

    /**
     * Running total of payment
     *
     * @return Running total
     */
    Total getTotal() {
        return total;
    }

    /**
     * Calculates and returns total change
     *
     * @return Total change
     */
    public Amount getChange() {
        return paidAmount.minus(total.getTotalWithTax());
    }

}
