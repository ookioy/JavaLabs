package ua.hotel_managment;

import java.time.LocalDate;
import java.util.Objects;

import ua.util.Utils;

/**
 * Represents a hotel guest with personal details and a check-in date.
 * Provides validation for all fields through setters.
 */
public class Guest {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate checkInDate; // check-in (registration) date

    /**
     * Constructs a new {@code Guest} instance with the provided details.
     * Validation is applied through setters.
     *
     * @param firstName   the guest's first name
     * @param lastName    the guest's last name
     * @param email       the guest's email
     * @param checkInDate the guest's check-in date
     * @throws IllegalArgumentException if any value is invalid
     * @throws NullPointerException     if any value is {@code null}
     */
    public Guest(String firstName, String lastName, String email, LocalDate checkInDate) {
        try {
            setFirstName(firstName);
            setLastName(lastName);
            setEmail(email);
            setCheckInDate(checkInDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid guest values. " + e.getMessage());
        } catch (NullPointerException e) {
            throw new NullPointerException("Invalid guest values. " + e.getMessage());
        }
    }

    /**
     * Factory method for creating a new {@code Guest}.
     *
     * @param firstName   the guest's first name
     * @param lastName    the guest's last name
     * @param email       the guest's email
     * @param checkInDate the guest's check-in date
     * @return a new {@code Guest} instance
     * @throws IllegalArgumentException if any value is invalid
     * @throws NullPointerException     if any value is {@code null}
     */
    public static Guest create(String firstName, String lastName, String email, LocalDate checkInDate) {
        return new Guest(firstName, lastName, email, checkInDate);
    }

    /**
     * Returns the guest's first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the guest's first name.
     *
     * @param firstName the first name
     * @throws NullPointerException     if {@code firstName} is {@code null}
     * @throws IllegalArgumentException if {@code firstName} is empty or blank
     */
    public void setFirstName(String firstName) {
        if (!Utils.validateObject(firstName) || !Utils.validateString(firstName)) {
            throw new NullPointerException("First name cannot be empty or null");
        }
        this.firstName = firstName;
    }

    /**
     * Returns the guest's last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the guest's last name.
     *
     * @param lastName the last name
     * @throws NullPointerException     if {@code lastName} is {@code null}
     * @throws IllegalArgumentException if {@code lastName} is empty or blank
     */
    public void setLastName(String lastName) {
        if (!Utils.validateObject(lastName) || !Utils.validateString(lastName)) {
            throw new IllegalArgumentException("Last name cannot be empty or null");
        }
        this.lastName = lastName;
    }

    /**
     * Returns the guest's email address.
     *
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Sets the guest's email address.
     *
     * @param email the email address
     * @throws NullPointerException     if {@code email} is {@code null}
     * @throws IllegalArgumentException if {@code email} does not match a valid format
     */
    public void setEmail(String email) {
        if (!Utils.validateObject(email)) {
            throw new NullPointerException("Email cannot be null");
        }
        else if (!Utils.validateEmail(email)) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        this.email = email;
    }

    /**
     * Returns the guest's check-in date.
     *
     * @return the check-in date
     */
    public LocalDate getCheckInDate() { return checkInDate; }

    /**
     * Sets the guest's check-in date.
     *
     * @param checkInDate the check-in date
     * @throws NullPointerException     if {@code checkInDate} is {@code null}
     * @throws IllegalArgumentException if {@code checkInDate} is in the past
     */
    public void setCheckInDate(LocalDate checkInDate) {
        if (!Utils.validateObject(checkInDate)) {
            throw new NullPointerException("Check-in date cannot be null");
        }
        else if (!Utils.validateDate(checkInDate, LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }
        this.checkInDate = checkInDate;
    }

    /**
     * Checks if this guest is equal to another object.
     * Two guests are equal if all fields match.
     *
     * @param obj the object to compare
     * @return {@code true} if the given object is a {@code Guest} with the same details, otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        Guest other = (Guest) obj;
        return Objects.equals(checkInDate, other.checkInDate)
                && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName);
    }

    /**
     * Returns a hash code for this guest.
     * Based on all fields to maintain consistency with {@link #equals(Object)}.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(checkInDate, email, firstName, lastName);
    }

    /**
     * Returns a string representation of the guest.
     * The format includes first name, last name, email, and check-in date.
     *
     * @return a string with the guest's details
     */
    @Override
    public String toString() {
        return "Guest [firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", checkInDate=" + checkInDate + "]";
    }
}