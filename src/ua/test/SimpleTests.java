package ua.test;

import ua.hotel_managment.Guest;
import java.time.LocalDate;
import java.util.logging.Logger;

public class SimpleTests {
    private static final Logger LOGGER = Logger.getLogger(SimpleTests.class.getName());

    public static void runTests() {
        LOGGER.info("Running basic tests...");
        testValidGuestCreation();
        testInvalidGuestEmail();
        testPastDateGuest();
        LOGGER.info("Tests completed.");
    }

    private static void testValidGuestCreation() {
        try {
            Guest guest = new Guest("Test", "User", "test@example.com", LocalDate.now().plusDays(1));
            if (guest != null) {
                System.out.println("[PASS] Valid guest creation");
            }
        } catch (Exception e) {
            System.err.println("[FAIL] Valid guest creation threw exception: " + e.getMessage());
        }
    }

    private static void testInvalidGuestEmail() {
        try {
            new Guest("Test", "User", "invalid-email", LocalDate.now().plusDays(1));
            System.err.println("[FAIL] Invalid email did not throw exception");
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] Invalid email caught: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[FAIL] Unexpected exception type: " + e.getClass().getSimpleName());
        }
    }

    private static void testPastDateGuest() {
        try {
            new Guest("Test", "User", "test@example.com", LocalDate.now().minusDays(1));
            System.err.println("[FAIL] Past date did not throw exception");
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] Past date caught: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[FAIL] Unexpected exception type: " + e.getClass().getSimpleName());
        }
    }
}