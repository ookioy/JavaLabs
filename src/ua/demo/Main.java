package ua.demo;

import ua.hotel_managment.*;
import java.time.LocalDate;
import ua.util.Utils;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRATION OF GUEST AND ROOM CLASSES ===\n");

        // --- 1. Створення об’єктів різними способами ---
        System.out.println(">>> Creating objects in different ways:");

        // через конструктор
        Guest guest1 = new Guest(
                "John",
                "Doe",
                "john.doe@example.com",
                LocalDate.now().plusDays(1)
        );

        // через фабричний метод create()
        Guest guest2 = Guest.create(
                "Alice",
                "Brown",
                "alice.brown@example.com",
                LocalDate.now().plusDays(2)
        );

        // створення кімнат різними способами
        Room room1 = new Room(101, "Single", 1, 500.0);
        Room room2 = Room.create(202, "Double", 2, 750.0);

        System.out.println(guest1);
        System.out.println(guest2);
        System.out.println(room1);
        System.out.println(room2);

        // --- 2. Демонстрація валідації ---
        System.out.println("\n>>> Demonstrating validation (successful and failed cases):");

        // успішний випадок
        try {
            Guest validGuest = new Guest("Emma", "White", "emma.white@example.com", LocalDate.now().plusDays(3));
            System.out.println("Valid guest created: " + validGuest);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        // неуспішний випадок: порожнє ім’я
        try {
            Guest invalidGuest = new Guest("", "Stone", "invalid@example.com", LocalDate.now().plusDays(1));
            System.out.println(invalidGuest);
        } catch (Exception e) {
            System.out.println("Invalid guest (empty first name): " + e.getMessage());
        }

        // неуспішний випадок: неправильний email
        try {
            Guest invalidEmail = new Guest("Tom", "Green", "wrong_email", LocalDate.now().plusDays(1));
            System.out.println(invalidEmail);
        } catch (Exception e) {
            System.out.println("Invalid guest (email format): " + e.getMessage());
        }

        // неуспішний випадок: минула дата
        try {
            Guest invalidDate = new Guest("Sara", "Moon", "sara.moon@example.com", LocalDate.now().minusDays(2));
            System.out.println(invalidDate);
        } catch (Exception e) {
            System.out.println("Invalid guest (past date): " + e.getMessage());
        }

        // неуспішний випадок: від’ємна ціна
        try {
            Room invalidRoom = new Room(303, "Suite", 2, -200.0);
            System.out.println(invalidRoom);
        } catch (Exception e) {
            System.out.println("Invalid room (negative price): " + e.getMessage());
        }

        // --- 3. Форматування та утиліти ---
        System.out.println("\n>>> Demonstrating formatting using Utils:");

        System.out.println("Email valid? " + Utils.validateEmail("example@mail.com"));
        System.out.println("Email valid? " + Utils.validateEmail("wrong@@mail"));

        // --- 4. Різні сценарії використання (успішні / неуспішні) ---
        System.out.println("\n>>> Demonstrating different use cases:");

        // зміна даних через сетери
        guest1.setLastName("Doe-Smith");
        room1.setPrice(600.0);
        System.out.println("Updated guest: " + guest1);
        System.out.println("Updated room: " + room1);

        // спроба задати некоректну ціну
        try {
            room2.setPrice(0);
        } catch (Exception e) {
            System.out.println("Unsuccessful case (zero price): " + e.getMessage());
        }

        System.out.println("\n=== END OF DEMONSTRATION ===");
    }
}