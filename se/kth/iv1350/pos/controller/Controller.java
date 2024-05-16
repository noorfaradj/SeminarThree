package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.model.Payment;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;

/**
 * The controller of application. All calls to the model pass through here.
 */
public class Controller {

    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private ItemCatalog itemCatalog;
    private Printer printer;
    private CashRegister cashRegister;
    private Sale sale;

    /**
     * New instance
     *
     * @param systemCreator Used to access classes for external system calls.
     * @param catalogCreator Used to access classes for database calls.
     * @param printer Interface to the printer.
     */
    public Controller(SystemCreator systemCreator, CatalogCreator catalogCreator, Printer printer) {
        this.accountingSystem = systemCreator.getAccountingSystem();
        this.inventorySystem = systemCreator.getInventorySystem();
        this.itemCatalog = catalogCreator.getItemCatalog();
        this.printer = printer;
        this.cashRegister = new CashRegister();
    }

    /**
     * Initiates a new sale.
     */
    public void startNewSale() {
        this.sale = new Sale();
    }

    /**
     * Registers an item in the current sale. If the item exists in the catalog,
     * it is added to the sale and information about the item along with the
     * running total is returned. Otherwise, only the running total is returned.
     *
     * @param itemIdentifier The identifier of the item to be added.
     * @param quantity The quantity of the item.
     * @return If {@link Item} true, A string containing information about the
     * item and the running total will return, else only the running total
     */
    public Item registerItem(String itemIdentifier, Amount quantity) {
        if (itemCatalog.itemExists(itemIdentifier)) {
            Item item = itemCatalog.getItem(itemIdentifier, quantity);
            sale.updateSale(item);
            return item;
        }
        return null;
    }

    /**
     * Amount with included tax
     *
     * @return Amount with included tax
     */
    public Amount displayTotalWithTax() {
        return sale.getTotal().getTotalWithTax();
    }

    /**
     * Amount
     *
     * @return Amount
     */
    public Amount displayTotal() {
        return sale.getTotal().getTotal();
    }

    /**
     * Processes a payment for the sale. Adds the {@link Amount} to the cash
     * register balance, updates external systems, prints a receipt, and returns
     * the change amount.
     *
     * @param paidAmount Paid amount by customer
     * @return Total change given to customer
     */
    public Amount pay(Amount paidAmount) {
        Payment payment = new Payment(paidAmount, sale.getTotal());
        cashRegister.addPayment(payment);
        accountingSystem.recordSale(sale);
        inventorySystem.updateInventory(sale);
        Receipt receipt = new Receipt(sale);
        printer.printReceipt(receipt);
        sale = null;
        return payment.getChange();
    }
}
