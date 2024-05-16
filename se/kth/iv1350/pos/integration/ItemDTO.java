package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.util.Amount;

/**
 * Represents an item's data.
 */
public class ItemDTO {

    private final Amount price; // The price of the item
    private final String itemName; // The name of the item
    private final Amount tax; // The tax amount of the item

    /**
     * Creates a new instance representing a specific item.
     *
     * @param price The price of the item.
     * @param itemName The name of the item.
     * @param tax The tax amount of the item.
     */
    public ItemDTO(Amount price, String itemName, Amount tax) {
        this.price = price;
        this.itemName = itemName;
        this.tax = tax;
    }

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    public Amount getPrice() {
        return price;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Retrieves the tax amount of the item.
     *
     * @return The tax amount of the item.
     */
    public Amount getTax() {
        return tax;
    }

    /**
     * Converts the item's data into a string.
     *
     * @return The item's data as a string.
     */
    @Override
    public String toString() {
        return "Item name: " + itemName + "\t"
                + "Price: " + price + "\t"
                + "Tax amount: " + tax + "\t";
    }

    /**
     * Checks if two ItemDTO objects are equal.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ItemDTO other = (ItemDTO) obj;
        return price.equals(other.price)
                && itemName.equals(other.itemName)
                && tax.equals(other.tax);
    }
}
