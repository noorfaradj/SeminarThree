package se.kth.iv1350.pos.util;

import java.util.Objects;

/**
 * Represents a quantity or amount, such as money or items.
 */
public class Amount {

    private int amount;

    /**
     * Creates an instance representing the specified amount.
     *
     * @param amount The numerical value represented by this instance.
     */
    public Amount(int amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the numerical value of this amount.
     *
     * @return The numerical value represented by this amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Converts this Amount object to a String.
     *
     * @return The string representation of this Amount object.
     */
    @Override
    public String toString() {
        return Integer.toString(amount);
    }

    /**
     * Checks if this amount is equal to another object.
     *
     * @param obj The object to compare against.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Amount other = (Amount) obj;
        return Objects.equals(this.amount, other.amount);
    }

    /**
     * Subtracts another Amount from this Amount.
     *
     * @param other The Amount to subtract from this Amount.
     * @return A new Amount representing the difference between this and the
     * other Amount.
     */
    public Amount minus(Amount other) {
        return new Amount(this.amount - other.amount);
    }

    /**
     * Adds another Amount to this Amount.
     *
     * @param other The Amount to add to this Amount.
     * @return A new Amount representing the sum of this and the other Amount.
     */
    public Amount plus(Amount other) {
        return new Amount(this.amount + other.amount);
    }

    /**
     * Multiplies this Amount by another Amount.
     *
     * @param other The Amount to multiply this Amount by.
     * @return A new Amount representing the product of this and the other
     * Amount.
     */
    public Amount multiply(Amount other) {
        return new Amount(this.amount * other.amount);
    }

}
