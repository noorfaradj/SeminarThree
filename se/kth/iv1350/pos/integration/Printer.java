package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Receipt;

/**
 * A virtual representation of a printer.
 */
public class Printer {

    /**
     * Constructs a new Printer instance.
     */
    public Printer() {
    }

    /**
     * Prints the specified receipt to the console.
     *
     * @param receipt The receipt to be printed.
     */
    public void printReceipt(Receipt receipt) {
        System.out.println("Printing receipt...");
        System.out.println(receipt.toString());
    }
}
