package ua.test;

import org.junit.jupiter.api.Test;
import ua.hotel_managment.Guest;
import ua.hotel_managment.Room;
import ua.repository.GenericRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lab4Tests {

    @Test
    void testRepositoryAddAndFindGuest() {
        // Створюємо репозиторій для Guest, де ID = email
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest guest = new Guest("Repo", "Tester", "repo@test.com", LocalDate.now().plusDays(1));

        repo.add(guest);

        Guest found = repo.findByIdentity("repo@test.com");
        assertNotNull(found, "Guest should be found in repository");
        assertEquals("Repo", found.firstName());
    }

    @Test
    void testRepositoryDuplicateHandling() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest g1 = new Guest("User1", "Test", "dup@test.com", LocalDate.now().plusDays(1));
        Guest g2 = new Guest("User2", "Test", "dup@test.com", LocalDate.now().plusDays(2)); // Той самий email

        repo.add(g1);
        repo.add(g2); // Не має додатися, бо ID (email) зайнятий

        assertEquals(1, repo.getAll().size(), "Repository should contain only 1 item");
        assertEquals("User1", repo.findByIdentity("dup@test.com").firstName(), "First object should be preserved");
    }

    @Test
    void testRepositoryWithRooms() {
        // Перевіряємо, що Generic працює і для Room (ID = номер кімнати)
        GenericRepository<Room> repo = new GenericRepository<>(r -> String.valueOf(r.getRoomNumber()));

        Room r1 = new Room(101, "Single", 1, 100.0);
        Room r2 = new Room(102, "Double", 2, 200.0);

        repo.add(r1);
        repo.add(r2);

        assertEquals(2, repo.getAll().size());
        assertNotNull(repo.findByIdentity("101"));
        assertNull(repo.findByIdentity("999")); // Такої кімнати немає
    }

    @Test
    void testRemoveFromRepository() {
        GenericRepository<Guest> repo = new GenericRepository<>(Guest::email);
        Guest guest = new Guest("To", "Remove", "remove@me.com", LocalDate.now().plusDays(1));
        repo.add(guest);

        repo.remove("remove@me.com");

        assertNull(repo.findByIdentity("remove@me.com"), "Guest should be removed");
        assertEquals(0, repo.getAll().size());
    }
}