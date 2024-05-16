package se.kth.iv1350.pos.integration;

/**
 * The SystemCreator class initializes essential systems required for the
 * application, including the accounting system and the inventory system.
 */
public class SystemCreator {

    private AccountingSystem accountingSystem; // Manages accounting-related functionalities
    private InventorySystem inventorySystem; // Manages inventory-related functionalities

    /**
     * Constructs a new instance of SystemCreator and initializes the accounting
     * and inventory systems.
     */
    public SystemCreator() {
        accountingSystem = new AccountingSystem();
        inventorySystem = new InventorySystem();
    }

    /**
     * Retrieves the accounting system instance.
     *
     * @return The accounting system instance.
     */
    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    /**
     * Retrieves the inventory system instance.
     *
     * @return The inventory system instance.
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }
}
