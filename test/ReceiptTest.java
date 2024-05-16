package se.kth.iv1350.pos.test;

import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.integration.ItemDTO;
import org.junit.Test;
import se.kth.iv1350.pos.util.Amount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class ReceiptTest {

    @Test
    public void testToString() {
        String itemNameAndIdentifier = "Kyckling";
        Amount quantity = new Amount(1);
        Item item = createItem(itemNameAndIdentifier, quantity);
        Sale sale = updateSaleWithItem(new Sale(), item);
        Receipt receipt = new Receipt(sale);
        String expectedSaleTime = formatSaleTime(LocalDateTime.now());
        String expectedResult = createExpectedResult(itemNameAndIdentifier, item, expectedSaleTime);
        String actualResult = receipt.toString();
        assertEquals("Receipt string is not equal to another instance with same state.", expectedResult, actualResult);
    }

    private Item createItem(String itemNameAndIdentifier, Amount quantity) {
        Amount price = new Amount(20);
        Amount tax = new Amount(5);
        ItemDTO itemDescription = new ItemDTO(price, itemNameAndIdentifier, tax);
        return new Item(itemDescription, itemNameAndIdentifier, quantity);
    }

    private Sale updateSaleWithItem(Sale sale, Item item) {
        sale.updateSale(item);
        return sale;
    }

    private String formatSaleTime(LocalDateTime saleTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return saleTime.format(formatter);
    }

    private String createExpectedResult(String itemNameAndIdentifier, Item item, String expectedSaleTime) {
        Amount price = item.getItemDescription().getPrice();
        Amount tax = item.getItemDescription().getTax();
        Amount quantity = item.getQuantity();
        return """
                   
                   ---------- RECEIPT ----------
                   Sale time: """ + " " + expectedSaleTime
                + "\nItems: "
                + "\nItem name: " + itemNameAndIdentifier + "\t"
                + "Price: " + price + "\t"
                + "Tax amount: " + tax + "\t"
                + " Quantity: " + quantity + "\nTotal: " + price + "\nTax: " + tax + "\n"
                + "\nSent sale info to external accounting system."
                + "\nDecreased quantity of sold items in inventory system."
                + "\n------------ END ------------\n";
    }
}
