package ua.demo;

import ua.hotel_managment.Guest;
import ua.hotel_managment.Room;
import ua.repository.GuestRepository;
import ua.repository.RoomRepository;
import ua.util.FileLoader;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        setupLogging();

        System.out.println("=== LAB 5: COLLECTIONS & SORTING ===\n");

        System.out.println(">>> Guest Repository Operations:");
        GuestRepository guestRepo = new GuestRepository();

        try {
            var guests = FileLoader.loadGuestsFromFile("guests.csv");
            guests.forEach(guestRepo::add);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        System.out.println("\n-- Sorted by Identity (Email) DESC --");
        List<Guest> byEmail = guestRepo.sortByIdentity("desc");
        byEmail.forEach(g -> System.out.println(g.email() + " : " + g.lastName()));

        System.out.println("\n-- Sorted by First Name (Method Ref) --");
        guestRepo.sortByFirstName().forEach(g -> System.out.println(g.firstName()));

        System.out.println("\n>>> Room Repository Operations:");
        RoomRepository roomRepo = new RoomRepository();
        roomRepo.add(new Room(305, "Suite", 4, 500.0));
        roomRepo.add(new Room(101, "Single", 1, 100.0));
        roomRepo.add(new Room(202, "Double", 2, 200.0));

        System.out.println("\n-- Natural Order (Comparable: Room Number) --");
        List<Room> rooms = roomRepo.getAll();
        Collections.sort(rooms);
        rooms.forEach(System.out::println);

        System.out.println("\n-- Sorted by Price (Custom Comparator) --");
        roomRepo.sortByPrice().forEach(System.out::println);

        System.out.println("\n=== END OF LAB 5 DEMONSTRATION ===");
    }

    private static void setupLogging() {
        try {
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fileHandler);
            rootLogger.setLevel(Level.INFO);
        } catch (IOException e) {
            System.err.println("Failed to setup logger: " + e.getMessage());
        }
    }
}