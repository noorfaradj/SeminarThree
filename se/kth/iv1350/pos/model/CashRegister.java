package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.util.Amount;

/**
 * This class represents a cash register used in the Point of Sale system. It
 * maintains the current balance of the cash register and updates it based on
 * the payments received.
 */
public class CashRegister {

    private Amount balance; // Represents the current balance of the cash register

    /**
     * Constructs a new instance of a cash register with a zero initial balance.
     */
    public CashRegister() {
        this.balance = new Amount(0);
    }

    /**
     * Retrieves the current balance of the cash register.
     *
     * @return The current balance.
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Updates the balance by adding the total amount of the payment.
     *
     * @param payment The payment made by the customer.
     */
    public void addPayment(Payment payment) {
        // Increase the balance by the total amount of the payment, including taxes
        balance = balance.plus(payment.getTotal().getTotalWithTax());
    }
}
