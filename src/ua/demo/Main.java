package ua.demo;

import ua.hotel_managment.*;
import ua.hotel_managment.enums.*;
import ua.repository.GenericRepository;
import ua.util.FileLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        setupLogging();

        System.out.println("=== LAB 4: GENERICS & REPOSITORY ===\n");

        // Юніт-тести запускаються окремо в IDE, тому прибираємо виклик SimpleTests

        System.out.println(">>> 1. Creating Generic Repository for Guests:");
        GenericRepository<Guest> guestRepo = new GenericRepository<>(Guest::email);

        try {
            var guestsFromFile = FileLoader.loadGuestsFromFile("guests.csv");
            for (Guest g : guestsFromFile) {
                guestRepo.add(g);
            }
        } catch (Exception e) {
            System.err.println("Error loading initial data: " + e.getMessage());
        }

        System.out.println("\n>>> 2. Creating Generic Repository for Rooms:");
        // Лямбда-вираз для отримання ID кімнати (номер)
        GenericRepository<Room> roomRepo = new GenericRepository<>(r -> String.valueOf(r.getRoomNumber()));

        Room r1 = new Room(101, "Single", 1, 100.0);
        Room r2 = new Room(102, "Double", 2, 150.0);
        Room r3 = new Room(101, "Single Duplicated", 1, 100.0);

        roomRepo.add(r1);
        roomRepo.add(r2);
        System.out.println("Attempting to add duplicate Room 101:");
        roomRepo.add(r3);

        System.out.println("\n>>> 3. Searching by Identity:");
        Guest foundGuest = guestRepo.findByIdentity("john@continental.com");
        if (foundGuest != null) {
            System.out.println("Found Guest: " + foundGuest.firstName() + " " + foundGuest.lastName());
        }

        Room foundRoom = roomRepo.findByIdentity("102");
        if (foundRoom != null) {
            System.out.println("Found Room: " + foundRoom.getRoomNumber() + " (" + foundRoom.getType() + ")");
        }

        System.out.println("\n>>> 4. Listing All Items in Room Repository:");
        for (Room r : roomRepo.getAll()) {
            System.out.println(r);
        }

        System.out.println("\n=== END OF LAB 4 DEMONSTRATION ===");
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