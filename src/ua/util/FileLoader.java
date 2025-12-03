package ua.util;

import ua.exceptions.InvalidDataException;
import ua.hotel_managment.Guest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLoader {
    private static final Logger LOGGER = Logger.getLogger(FileLoader.class.getName());

    public static List<Guest> loadGuestsFromFile(String filePath) throws InvalidDataException {
        List<Guest> guests = new ArrayList<>();
        LOGGER.info("Starting to load guests from file: " + filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                try {
                    guests.add(parseGuest(line));
                } catch (InvalidDataException e) {
                    LOGGER.log(Level.WARNING, "Skipping invalid line " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found: " + filePath, e);
            throw new InvalidDataException("File not found: " + filePath, e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: " + filePath, e);
            throw new InvalidDataException("Error reading file", e);
        }

        LOGGER.info("Successfully loaded " + guests.size() + " guests.");
        return guests;
    }

    private static Guest parseGuest(String line) throws InvalidDataException {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new InvalidDataException("Invalid format. Expected 4 fields, got " + parts.length);
        }

        try {
            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            String email = parts[2].trim();
            LocalDate checkInDate = LocalDate.parse(parts[3].trim());

            return new Guest(firstName, lastName, email, checkInDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Invalid date format. Expected YYYY-MM-DD.", e);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidDataException("Validation failed: " + e.getMessage(), e);
        }
    }
}