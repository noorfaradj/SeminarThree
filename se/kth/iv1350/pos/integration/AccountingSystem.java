package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Sale;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Represents a simulated external accounting system.
 */
public class AccountingSystem {

    private HashMap<LocalDateTime, Sale> accounting = new HashMap<>();

    /**
     * Creates a new instance of the accounting system.
     */
    AccountingSystem() {
    }

    /**
     * Records a completed sale in the accounting system.
     *
     * @param sale The completed sale to be recorded.
     */
    public void recordSale(Sale sale) {
        LocalDateTime saleTime = LocalDateTime.now();
        accounting.put(saleTime, sale);
    }
}
