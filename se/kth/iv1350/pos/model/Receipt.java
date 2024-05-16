package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a receipt.
 */
public class Receipt {

    private final Sale sale;

    /**
     * Creates a new instance of Receipt with the specified Sale.
     *
     * @param sale The sale for which the receipt is generated.
     */
    public Receipt(Sale sale) {
        this.sale = sale;
    }

    /**
     * Converts the Receipt instance into a formatted string.
     *
     * @return A string representation of the receipt.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        addNewLine(builder);
        appendLine(builder, "---------- RECEIPT ----------");
        addSaleDateTime(builder);
        appendLine(builder, "Items: ");
        appendLine(builder, sale.toString());
        appendLine(builder, "Sent sale info to external accounting system.");
        appendLine(builder, "Decreased quantity of sold items in inventory system.");
        appendLine(builder, "------------ END ------------");
        return builder.toString();
    }

    private void addNewLine(StringBuilder builder) {
        builder.append("\n");
    }

    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        addNewLine(builder);
    }

    private void addSaleDateTime(StringBuilder builder) {
        LocalDateTime saleDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = saleDateTime.format(formatter); // Format the LocalDateTime object
        appendLine(builder, "Sale time: " + formattedDateTime);
    }
}
