package ua.util;

import java.time.LocalDate;

/**
 * Package-private helper class providing basic validation methods.
 * This class is not visible outside the ua.util package.
 * Methods are static and meant to be used internally by other classes (e.g., Utils).
 */
abstract class ValidationHelper {

    /**
     * Checks if a string is not null and not empty (ignores whitespace).
     *
     * @param value the string to check
     * @return true if string is not null and contains non-space characters
     */
    static boolean isNotEmpty(String value) {
        return !value.trim().isEmpty();
    }

    /**
     * Checks if an email has a valid basic format.
     *
     * @param email the email string to check
     * @return true if email matches the basic regex pattern
     */
    static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$");
    }

    /**
     * Checks if the compare date is equal or before the threshold date.
     *
     * @param compareDate the date to validate
     * @param thresholdDate date to compare with
     * @return true if the date is today or later
     */
    static boolean isValidCompareDate(LocalDate compareDate, LocalDate thresholdDate) {
        return !compareDate.isBefore(thresholdDate);
    }

    /**
     * Checks if a integer number is positive.
     *
     * @param number the integer number to validate
     * @return true if the number is positive
     */
    static boolean isPositiveNumber (int number) {
        return number > 0;
    }

    /**
     * Checks if a double number is positive.
     *
     * @param number double number to validate
     * @return true if the number is positive
     */
    static boolean isPositiveNumber(double number) {
        return number > 0;
    }

    /**
     * Checks if a double number is positive.
     *
     * @param number double number to validate
     * @return true if the number is positive
     */
    static boolean isNegativeNumber(double number) {
        return number < 0;
    }

    /**
     * Checks if an object isn't null.
     *
     * @param o object to validate
     * @return true if object is null
     */
    static boolean isObjectNull(Object o) {
        return o == null;
    }
}