package se.kth.iv1350.pos.model;

import java.util.HashMap;
import se.kth.iv1350.pos.integration.Item;

/**
 * Represents a sale transaction between a customer and a cashier.
 *
 * The HashMap in the Sale class is used to store the items that are part of the
 * sale. It provides a way to associate each item with a unique identifier (in
 * this case, the item identifier) for quick and efficient retrieval.
 */
public class Sale {

    private final Total total;
    private final HashMap<String, Item> items = new HashMap<>();

    /**
     * Creates a new instance of a sale.
     */
    public Sale() {
        this.total = new Total();
    }

    /**
     * Retrieves the total of the current sale.
     *
     * @return The total of the sale.
     */
    public Total getTotal() {
        return total;
    }

    /**
     * Retrieves the items in the current sale.
     *
     * @return The items in the sale.
     */
    public final HashMap<String, Item> getItems() {
        return items;
    }

    /**
     * Updates the current sale by adding an item. If the item already exists in
     * the sale, updates its quantity and total.
     *
     * @param item The item to be added to the sale.
     * @return The item description as a string.
     */
    public String updateSale(Item item) {
        if (containsItem(item)) {
            updateExistingItem(item);
        } else {
            addNewItem(item);
        }
        return item.getItemDescription().toString();
    }

    private boolean containsItem(Item item) {
        return items.containsKey(item.getItemIdentifier());
    }

    private void updateExistingItem(Item item) {
        Item existingItem = items.get(item.getItemIdentifier());
        existingItem.increaseQuantity(item.getQuantity());
        total.updateTotal(item);
    }

    private void addNewItem(Item item) {
        items.put(item.getItemIdentifier(), item);
        total.updateTotal(item);
    }

    /**
     * Converts the sale instance to a string representation.
     *
     * @return The sale details as a string.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Item item : items.values()) {
            builder.append(item.getItemDescription()).append(" Quantity: ").append(item.getQuantity()).append("\n");
        }

        builder.append("Total: ").append(total.getTotal()).append("\n");
        builder.append("Tax: ").append(total.getTotalTax()).append("\n");
        return builder.toString();
    }
}
