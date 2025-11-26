package ua.demo;

import ua.hotel_managment.*;
import ua.hotel_managment.enums.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== LAB 2: RECORDS, ENUMS & SWITCH EXPRESSIONS ===\n");

        System.out.println(">>> Creating Guest and Service (Records):");

        Guest guest = new Guest("John", "Wick", "john@continental.com", LocalDate.now().plusDays(1));
        System.out.println("Guest Record created: " + guest.firstName() + " " + guest.lastName());

        Service spa = new Service("Spa Access", 100);
        Service dinner = new Service("Gourmet Dinner", 50);
        System.out.println("Service Record created: " + spa.name() + " ($" + spa.price() + ")");

        System.out.println("\n>>> Creating Room and Reservation with Enums:");

        Room room = new Room(305, "Suite", 2, 500.0, RoomStatus.AVAILABLE);
        System.out.println("Room created: " + room);

        Reservation reservation = new Reservation(guest, room, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));
        reservation.addService(spa);
        reservation.addService(dinner);

        System.out.println("Reservation created with status: " + reservation.getStatus());

        reservation.setStatus(ReservationStatus.CHECKED_IN);
        room.setStatus(RoomStatus.OCCUPIED);

        System.out.println("Reservation updated status: " + reservation.getStatus());
        System.out.println("Room updated status: " + room.getStatus());

        System.out.println("\n>>> Demonstrating Switch Expressions:");

        String roomAction = switch (room.getStatus()) {
            case AVAILABLE -> "Room is ready for new guests.";
            case OCCUPIED -> "Room is currently taken.";
            case CLEANING -> "Housekeeping is working.";
            case MAINTENANCE -> "Room is closed for repairs.";
        };
        System.out.println("Action for Room " + room.getRoomNumber() + ": " + roomAction);

        double discount = getDiscountByStatus(reservation.getStatus());
        System.out.println("Applied discount for status " + reservation.getStatus() + ": " + (discount * 100) + "%");

        System.out.println("\n>>> Invoice Calculation:");
        Invoice invoice = new Invoice(reservation, LocalDate.now());
        invoice.calculateTotalAmount();
        System.out.println("Invoice generated for: " + invoice.getReservation().getGuest().lastName());
        System.out.println("Total Amount to pay: " + invoice.getTotalAmount());

        System.out.println("\n=== END OF LAB 2 DEMONSTRATION ===");
    }

    private static double getDiscountByStatus(ReservationStatus status) {
        return switch (status) {
            case CONFIRMED -> 0.05;
            case CHECKED_IN, CANCELED -> 0.0;
            case CHECKED_OUT -> 0.10;
        };
    }
}