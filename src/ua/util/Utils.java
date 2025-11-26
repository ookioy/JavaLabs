package ua.util;

import java.time.LocalDate;

public abstract class Utils {

    public static boolean validateEmail(String email) {
        return ValidationHelper.isValidEmail(email);
    }

    public static boolean validateString(String text) {
        return ValidationHelper.isNotEmpty(text);
    }

    public static boolean validateDate(LocalDate compareDate, LocalDate thresholdDate) {
        return ValidationHelper.isValidCompareDate(compareDate, thresholdDate);
    }

    public static boolean validatePositiveNumber(int number) {
        return ValidationHelper.isPositiveNumber(number);
    }

    public static boolean validatePositiveNumber(double number) {
        return ValidationHelper.isPositiveNumber(number);
    }

    public static boolean validateObject(Object o) {
        return !ValidationHelper.isObjectNull(o);
    }
}