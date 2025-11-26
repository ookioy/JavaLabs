package ua.util;

import java.time.LocalDate;

abstract class ValidationHelper {

    static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$");
    }

    static boolean isValidCompareDate(LocalDate compareDate, LocalDate thresholdDate) {
        return !compareDate.isBefore(thresholdDate);
    }

    static boolean isPositiveNumber(int number) {
        return number > 0;
    }

    static boolean isPositiveNumber(double number) {
        return number > 0;
    }

    static boolean isObjectNull(Object o) {
        return o == null;
    }
}