package ua.util;

import java.time.LocalDate;

/**
 * Public utility class that provides access to validation methods.
 * It acts as a facade to the internal ValidationHelper class.
 */
public abstract class Utils {

    /**
     * Validates if the given email has a correct format.
     *
     * @param email the email string to validate
     * @return true if the email is valid
     */
    public static boolean validateEmail(String email) {
        return ValidationHelper.isValidEmail(email);
    }

    /**
     * Validates if a string is not null and not empty (ignores whitespace).
     *
     * @param text the string to validate
     * @return true if the string is valid
     */
    public static boolean validateString(String text) {
        return ValidationHelper.isNotEmpty(text);
    }

    /**
     * Validates if a compare date is equal to threshold date or after it.
     *
     * @param compareDate the date to validate
     * @param thresholdDate the date to compare with
     * @return true if the compareDate is valid
     */
    public static boolean validateDate(LocalDate compareDate, LocalDate thresholdDate) {
        return ValidationHelper.isValidCompareDate(compareDate, thresholdDate);
    }

    /**
     * Validates if a number is positive number.
     *
     * @param number integer value to validate
     * @return true if the number is positive
     */
    public static boolean validatePositiveNumber(int number) {
        return ValidationHelper.isPositiveNumber(number);
    }

    /**
     * Validates if a number is positive number.
     *
     * @param number double value to validate
     * @return true if the number is positive
     */
    public static boolean validatePositiveNumber(double number) {
        return ValidationHelper.isPositiveNumber(number);
    }

    /**
     * Validates if a number is positive number.
     *
     * @param number double value to validate
     * @return true if the number is positive
     */
    public static boolean validateNegativeNumber(double number) {
        return !ValidationHelper.isNegativeNumber(number);
    }

    /**
     * Validates if object (o) isn't null.
     *
     * @param o Object to validate
     * @return true if o is not null
     */
    public static boolean validateObject(Object o) {
        return !ValidationHelper.isObjectNull(o);
    }
}