package se.kth.iv1350.pos.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.CatalogCreator;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.SystemCreator;
import se.kth.iv1350.pos.controller.DatabaseFailureException;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.util.Amount;

import static org.junit.Assert.*;

public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller(SystemCreator.getSystemCreator(), CatalogCreator.getCatalogCreator(), Printer.getPrinter());
        controller.startNewSale();
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void registerItem() {
        String itemName = "Potatis";
        Amount price = new Amount(50);
        Amount tax = new Amount(15);
        try {
            String actualResult = controller.registerItem(itemName, new Amount(1));
            String expectedResult = buildExpectedResult(itemName, price, tax, new Amount(1), price);
            assertEquals("String from registerItem is not the same as String with the same state.", expectedResult, actualResult);
        } catch (DatabaseFailureException exc) {
            fail("Database failure exception occurred: " + exc.getMessage());
        } catch (ItemNotFoundException exc) {
            fail("Item not found exception occurred: " + exc.getMessage());
        }

    }

    @Test
    public void displayTotalWithTax() {
        String itemName = "Potatis";
        Amount price = new Amount(50);
        Amount tax = new Amount(15);
        try {
            controller.registerItem(itemName, new Amount(1));
        } catch (DatabaseFailureException | ItemNotFoundException exc) {
            fail("Exception occurred: " + exc.getMessage());
        }

        String actualResult = controller.displayTotalWithTax();
        String expectedResult = "Total with taxes: " + price.plus(tax);
        assertEquals("The total with tax from sale is not equal to the expected result.", expectedResult, actualResult);
    }

    @Test
    public void pay() {
        String itemName = "Potatis";
        Amount price = new Amount(50);
        Amount tax = new Amount(15);
        try {
            controller.registerItem(itemName, new Amount(1));
        } catch (DatabaseFailureException | ItemNotFoundException exc) {
            fail("Exception occurred: " + exc.getMessage());
        }
        Amount paidAmount = new Amount(40);
        String expectedResult = "Change: " + paidAmount.minus(price.plus(tax));
        String actualResult = controller.pay(paidAmount);
        assertEquals("Change is not equal to amount with the same amount.", expectedResult, actualResult);
    }

    private String buildExpectedResult(String itemName, Amount price, Amount tax, Amount quantity, Amount runningTotal) {
        return "Item name: " + itemName + "\t"
                + "Price: " + price + "\t"
                + "Tax amount: " + tax + "\t"
                + ", quantity: " + quantity + ", running total: " + runningTotal;
    }
}
