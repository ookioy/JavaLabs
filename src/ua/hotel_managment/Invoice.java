package ua.hotel_managment;

import java.time.LocalDate;
import java.util.Objects;
import ua.util.Utils;

public class Invoice {

    private Reservation reservation;
    private double totalAmount;
    private LocalDate issueDate;

    public Invoice(Reservation reservation, LocalDate issueDate) {
        try {
            setReservation(reservation);
            setIssueDate(issueDate);
            totalAmount = 0;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid invoice's value. " + e);
        }
    }

    public static Invoice create(Reservation reservation, LocalDate issueDate) {
        return new Invoice(reservation, issueDate);
    }

    public void calculateTotalAmount() {
        totalAmount = reservation.getRoom().getPrice();
        for(Service service : reservation.getServices()) {
            totalAmount += service.price();
        }
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        if (!Utils.validateObject(reservation)) {
            throw new NullPointerException("Reservation can't be null");
        }
        this.reservation = reservation;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        if (!Utils.validateObject(issueDate)) {
            throw new NullPointerException("Issue date can't be null");
        }
        if (!Utils.validateDate(issueDate, LocalDate.now())) {
            throw new IllegalArgumentException("Issue date can't be in the past");
        }
        this.issueDate = issueDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueDate, reservation, totalAmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Invoice other = (Invoice) obj;
        return Objects.equals(issueDate, other.issueDate)
                && Objects.equals(reservation, other.reservation)
                && Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount);
    }

    @Override
    public String toString() {
        return "Invoice [reservation=" + reservation
                + ", totalAmount=" + totalAmount
                + ", issueDate=" + issueDate + "]";
    }
}