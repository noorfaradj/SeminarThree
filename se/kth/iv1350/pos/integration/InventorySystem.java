package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.Amount;

/**
 * A placeholder class representing an external inventory system.
 */
public class InventorySystem {

    private final HashMap<String, Item> inventory = new HashMap<>();

    /**
     * Creates a new instance of the inventory system and initializes it with
     * dummy items.
     */
    InventorySystem() {
        initializeInventory();
    }

    /**
     * Updates the inventory by deducting sold items.
     *
     * @param sale The sale containing the items to be deducted from the
     * inventory.
     */
    public void updateInventory(Sale sale) {
        HashMap<String, Item> items = sale.getItems();

        for (Item item : items.values()) {
            if (itemExistsInInventory(item)) {
                deductItemQuantity(item);
            }
        }
    }

    private boolean itemExistsInInventory(Item item) {
        return inventory.containsKey(item.getItemIdentifier());
    }

    private void deductItemQuantity(Item item) {
        Item inventoryItem = inventory.get(item.getItemIdentifier());
        inventoryItem.decreaseQuantity(item.getQuantity());
        inventory.put(item.getItemIdentifier(), inventoryItem);
    }

    private void initializeInventory() {
        inventory.put("Kyckling", new Item(new ItemDTO(new Amount(100), "Kyckling", new Amount(10)), "Kyckling", new Amount(Integer.MAX_VALUE)));
        inventory.put("Potatis", new Item(new ItemDTO(new Amount(50), "Potatis", new Amount(15)), "Potatis", new Amount(Integer.MAX_VALUE)));
        inventory.put("Ärtor", new Item(new ItemDTO(new Amount(5), "Ärtor", new Amount(1)), "Ärtor", new Amount(Integer.MAX_VALUE)));
    }
}
