package ua.demo;

import ua.exceptions.InvalidDataException;
import ua.hotel_managment.*;
import ua.hotel_managment.enums.*;
import ua.util.FileLoader;
import ua.test.SimpleTests;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        setupLogging();

        System.out.println("=== LAB 3: EXCEPTIONS, FILES & LOGGING ===\n");

        SimpleTests.runTests();
        System.out.println();

        List<Guest> guests;
        try {
            guests = FileLoader.loadGuestsFromFile("guests.csv");
        } catch (InvalidDataException e) {
            LOGGER.log(Level.SEVERE, "Critical error loading data: " + e.getMessage());
            System.err.println("Failed to load guests. Exiting.");
            return;
        }

        if (guests.isEmpty()) {
            System.out.println("No guests loaded.");
        } else {
            Guest mainGuest = guests.getFirst();
            System.out.println(">>> Processing Main Guest: " + mainGuest.firstName());

            Room room = new Room(305, "Suite", 2, 500.0, RoomStatus.AVAILABLE);
            Reservation reservation = new Reservation(mainGuest, room, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));

            System.out.println("Reservation created: " + reservation);

            processReservationStatus(reservation);
        }

        System.out.println("\n=== END OF LAB 3 DEMONSTRATION ===");
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

    private static void processReservationStatus(Reservation reservation) {
        switch (reservation.getStatus()) {
            case CONFIRMED:
                System.out.println("Status: Confirmed. Waiting for check-in.");
                break;
            case CHECKED_IN:
                System.out.println("Status: Checked In. Guest is in the room.");
                break;
            default:
                System.out.println("Status: Other.");
                break;
        }
    }
}