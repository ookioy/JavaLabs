package ua.test;

import org.junit.jupiter.api.Test;
import ua.hotel_managment.Guest;
import ua.hotel_managment.Room;
import ua.repository.GuestRepository;
import ua.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Lab5Tests {

    @Test
    void testRoomComparable() {
        Room r1 = new Room(101, "S", 1, 100);
        Room r2 = new Room(202, "D", 2, 200);
        assertTrue(r1.compareTo(r2) < 0, "Room 101 should be less than 202");
    }

    @Test
    void testGuestRepositorySorting() {
        GuestRepository repo = new GuestRepository();
        repo.add(new Guest("Bob", "Builder", "b@test.com", LocalDate.now().plusDays(2)));
        repo.add(new Guest("Alice", "Wonder", "a@test.com", LocalDate.now().plusDays(1)));

        List<Guest> sorted = repo.sortByFirstName();

        assertEquals("Alice", sorted.get(0).firstName());
        assertEquals("Bob", sorted.get(1).firstName());
    }

    @Test
    void testRoomRepositorySortingByPrice() {
        RoomRepository repo = new RoomRepository();
        repo.add(new Room(101, "Cheap", 1, 100.0));
        repo.add(new Room(102, "Expensive", 1, 500.0));
        repo.add(new Room(103, "Mid", 1, 300.0));

        List<Room> sorted = repo.sortByPrice();

        assertEquals(100.0, sorted.get(0).getPrice());
        assertEquals(300.0, sorted.get(1).getPrice());
        assertEquals(500.0, sorted.get(2).getPrice());
    }

    @Test
    void testGenericSortByIdentity() {
        GuestRepository repo = new GuestRepository();
        repo.add(new Guest("Zack", "Sny", "z@test.com", LocalDate.now().plusDays(1)));
        repo.add(new Guest("Adam", "San", "a@test.com", LocalDate.now().plusDays(1)));

        List<Guest> sorted = repo.sortByIdentity("asc");
        assertEquals("a@test.com", sorted.get(0).email());

        List<Guest> sortedDesc = repo.sortByIdentity("desc");
        assertEquals("z@test.com", sortedDesc.get(0).email());
    }
}