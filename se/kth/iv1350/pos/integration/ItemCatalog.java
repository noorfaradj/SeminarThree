package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents a dummy item database.
 */
public class ItemCatalog {

    private final HashMap<String, ItemDTO> itemList = new HashMap<>();

    /**
     * Creates an instance of a dummy item database and initializes it with
     * dummy items.
     */
    ItemCatalog() {
        initializeItemDatabase();
    }

    /**
     * Checks if an item with the specified identifier exists in the database.
     *
     * @param itemIdentifier The identifier of the item to search for.
     * @return True if the item exists in the database, otherwise false.
     */
    public boolean itemExists(String itemIdentifier) {
        return itemList.containsKey(itemIdentifier);
    }

    /**
     * Retrieves the item with the specified identifier and quantity.
     *
     * @param itemIdentifier The identifier of the item to retrieve.
     * @param quantity The quantity of the item.
     * @return An Item object representing the specified item and quantity, or
     * null if the item does not exist.
     */
    public Item getItem(String itemIdentifier, Amount quantity) {
        if (itemExists(itemIdentifier)) {
            return new Item(itemList.get(itemIdentifier), itemIdentifier, quantity);
        }
        return null;
    }

    private void initializeItemDatabase() {
        itemList.put("Kyckling", new ItemDTO(new Amount(100), "Kyckling", new Amount(10)));
        itemList.put("Potatis", new ItemDTO(new Amount(50), "Potatis", new Amount(15)));
        itemList.put("Ärtor", new ItemDTO(new Amount(5), "Ärtor", new Amount(1)));
    }
}
