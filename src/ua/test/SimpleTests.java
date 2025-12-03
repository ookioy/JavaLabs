package ua.test;

import ua.hotel_managment.Guest;
import ua.repository.GenericRepository;
import java.time.LocalDate;
import java.util.logging.Logger;

public class SimpleTests {
    private static final Logger LOGGER = Logger.getLogger(SimpleTests.class.getName());

    public static void runTests() {
        LOGGER.info("Running basic tests...");
        testValidGuestCreation();
        testInvalidGuestEmail();
        testPastDateGuest();
        testRepositoryAddAndFind();
        testRepositoryDuplicate();
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

    private static void testRepositoryAddAndFind() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g = new Guest("Repo", "Tester", "repo@test.com", LocalDate.now().plusDays(1));
        repo.add(g);
        Guest found = repo.findByIdentity("repo@test.com");
        if (found != null && found.equals(g)) {
            System.out.println("[PASS] Repository Add and Find");
        } else {
            System.err.println("[FAIL] Repository could not find added item");
        }
    }

    private static void testRepositoryDuplicate() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g1 = new Guest("Repo", "Tester", "dup@test.com", LocalDate.now().plusDays(1));
        Guest g2 = new Guest("Repo", "Tester", "dup@test.com", LocalDate.now().plusDays(2));
        repo.add(g1);
        repo.add(g2);

        if (repo.getAll().size() == 1) {
            System.out.println("[PASS] Repository handled duplicate");
        } else {
            System.err.println("[FAIL] Repository allowed duplicate keys");
        }
    }
}