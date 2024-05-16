package se.kth.iv1350.pos.integration;

/**
 * Represents a factory for creating different catalogs.
 */
public class CatalogCreator {

    private final ItemCatalog itemCatalog;

    /**
     * Creates a new instance of the catalog factory.
     */
    public CatalogCreator() {
        itemCatalog = new ItemCatalog();
    }

    /**
     * Retrieves the item catalog.
     *
     * @return The item catalog.
     */
    public ItemCatalog getItemCatalog() {
        return itemCatalog;
    }
}
