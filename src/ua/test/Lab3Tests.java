package ua.test;

import org.junit.jupiter.api.Test;
import ua.hotel_managment.Guest;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Lab3Tests {

    @Test
    void testValidGuestCreation() {
        Guest guest = new Guest("John", "Doe", "john.doe@example.com", LocalDate.now().plusDays(1));

        assertNotNull(guest);
        assertEquals("John", guest.firstName());
        assertEquals("Doe", guest.lastName());
        assertEquals("john.doe@example.com", guest.email());
    }

    @Test
    void testGuestCreationWithInvalidEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Guest("John", "Doe", "invalid-email", LocalDate.now().plusDays(1));
        });

        assertEquals("Email format is invalid", exception.getMessage());
    }

    @Test
    void testGuestCreationWithPastDate() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Guest("John", "Doe", "john@example.com", LocalDate.now().minusDays(1));
        });

        assertEquals("Check-in date cannot be in the past", exception.getMessage());
    }

    @Test
    void testGuestCreationWithNullValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Guest(null, "Doe", "john@example.com", LocalDate.now().plusDays(1));
        });
    }

    @Test
    void testGuestCreationWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Guest("", "Doe", "john@example.com", LocalDate.now().plusDays(1));
        });
    }
}