package ua.hotel_managment;

import java.time.LocalDate;
import ua.util.Utils;

public record Guest(String firstName, String lastName, String email, LocalDate checkInDate) {

    public Guest {
        if (!Utils.validateObject(firstName) || !Utils.validateString(firstName)) {
            throw new IllegalArgumentException("First name cannot be empty or null");
        }
        if (!Utils.validateObject(lastName) || !Utils.validateString(lastName)) {
            throw new IllegalArgumentException("Last name cannot be empty or null");
        }
        if (!Utils.validateObject(email)) {
            throw new NullPointerException("Email cannot be null");
        } else if (!Utils.validateEmail(email)) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        if (!Utils.validateObject(checkInDate)) {
            throw new NullPointerException("Check-in date cannot be null");
        } else if (!Utils.validateDate(checkInDate, LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }
    }

    public static Guest create(String firstName, String lastName, String email, LocalDate checkInDate) {
        return new Guest(firstName, lastName, email, checkInDate);
    }
}