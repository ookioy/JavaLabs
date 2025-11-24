package ua.hotel_managment;

import java.time.LocalDate;
import java.util.Objects;

import ua.util.Utils;

/**
 * Represents an invoice for a hotel reservation.
 * An invoice contains the reservation details,
 * the total amount to be paid, and the issue date.
 */
public class Invoice {

    /** The reservation associated with this invoice. */
    private Reservation reservation;

    /** The total amount to be paid for the invoice. */
    private double totalAmount;

    /** The date the invoice was issued. */
    private LocalDate issueDate;

    /**
     * Constructor.
     * Creates a new Invoice object with the given reservation and issue date.
     *
     * @param reservation the reservation associated with this invoice
     * @param issueDate   the date the invoice is issued
     * @throws NullPointerException if the reservation or issue date is null
     * @throws IllegalArgumentException if the issue date is in the past
     */
    public Invoice(Reservation reservation, LocalDate issueDate) {
        try {
            setReservation(reservation);
            setIssueDate(issueDate);
            totalAmount = 0;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid invoice's value. " + e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Invalid invoice's value. " + e);
        }
    }

    /**
     * Factory method.
     * Creates a new Invoice object with the given reservation and issue date.
     *
     * @param reservation the reservation associated with this invoice
     * @param issueDate   the date the invoice is issued
     * @return a new Invoice object
     * @throws NullPointerException if the reservation or issue date is null
     * @throws IllegalArgumentException if the issue date is in the past
     */
    public static Invoice create(Reservation reservation, LocalDate issueDate) {
        return new Invoice(reservation, issueDate);
    }

    /**
     * Calculates the total amount of the invoice.
     * The total includes the room price and all associated services.
     */
    public void calculateTotalAmount() {
        totalAmount = reservation.getRoom().getPrice();
        for(Service service : reservation.getServices()) {
            totalAmount += service.getPrice();
        }
    }

    /**
     * Returns the reservation associated with this invoice.
     *
     * @return the reservation
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Sets the reservation for this invoice.
     *
     * @param reservation the reservation to set
     * @throws NullPointerException if the reservation is null
     */
    public void setReservation(Reservation reservation) {
        if (!Utils.validateObject(reservation)) {
            throw new NullPointerException("Reservation can't be null");
        }
        this.reservation = reservation;
    }

    /**
     * Returns the total amount of this invoice.
     *
     * @return the total amount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Returns the issue date of the invoice.
     *
     * @return the issue date
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the issue date of the invoice.
     *
     * @param issueDate the issue date to set
     * @throws NullPointerException if the issue date is null
     * @throws IllegalArgumentException if the issue date is in the past
     */
    public void setIssueDate(LocalDate issueDate) {
        if (!Utils.validateObject(issueDate)) {
            throw new NullPointerException("Issue date can't be null");
        }
        if (!Utils.validateDate(issueDate, LocalDate.now())) {
            throw new IllegalArgumentException("Issue date can't be in the past");
        }
        this.issueDate = issueDate;
    }

    /**
     * Generates a hash code for this invoice.
     *
     * @return hash code of the invoice
     */
    @Override
    public int hashCode() {
        return Objects.hash(issueDate, reservation, totalAmount);
    }

    /**
     * Compares this invoice with another object for equality.
     * Two invoices are equal if they have the same reservation,
     * issue date, and total amount.
     *
     * @param obj the object to compare
     * @return true if the invoices are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Invoice other = (Invoice) obj;
        return Objects.equals(issueDate, other.issueDate)
                && Objects.equals(reservation, other.reservation)
                && Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount);
    }

    /**
     * Returns a string representation of the invoice,
     * including reservation, total amount, and issue date.
     *
     * @return formatted string with invoice details
     */
    @Override
    public String toString() {
        return "Invoice [reservation=" + reservation
                + ", totalAmount=" + totalAmount
                + ", issueDate=" + issueDate + "]";
    }
}