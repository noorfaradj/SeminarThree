package se.kth.iv1350.pos.test;

import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.model.Total;
import se.kth.iv1350.pos.model.Payment;

import org.junit.Test;
import se.kth.iv1350.pos.util.Amount;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void changeTest() {
        String itemNameAndIdentifier = "Kyckling";
        Amount quantity = new Amount(2);
        Item item = createItem(itemNameAndIdentifier, quantity);
        Total total = updateTotalWithItem(new Total(), item);
        Amount paidAmount = new Amount(100);
        Payment payment = new Payment(paidAmount, total);
        Amount expectedResult = calculateExpectedResult(paidAmount, item);
        Amount actualResult = payment.getChange();
        assertEquals("Change is not the same.", expectedResult, actualResult);
    }

    private Item createItem(String itemNameAndIdentifier, Amount quantity) {
        Amount price = new Amount(20);
        Amount tax = new Amount(5);
        ItemDTO itemDescription = new ItemDTO(price, itemNameAndIdentifier, tax);
        return new Item(itemDescription, itemNameAndIdentifier, quantity);
    }

    private Total updateTotalWithItem(Total total, Item item) {
        total.updateTotal(item);
        return total;
    }

    private Amount calculateExpectedResult(Amount paidAmount, Item item) {
        Amount price = item.getItemDescription().getPrice();
        Amount tax = item.getItemDescription().getTax();
        Amount quantity = item.getQuantity();
        return paidAmount.minus(price.multiply(quantity).plus(tax.multiply(quantity)));
    }
}
