package ua.repository;

import ua.hotel_managment.Guest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GuestRepository extends GenericRepository<Guest> {

    public GuestRepository() {
        super(Guest::email);
    }

    public List<Guest> sortByFirstName() {
        LOGGER.info("Sorting guests by First Name");
        return storage.values().stream()
                .sorted(Comparator.comparing(Guest::firstName))
                .collect(Collectors.toList());
    }

    public List<Guest> sortByCheckInDate(boolean descending) {
        LOGGER.info("Sorting guests by Check-in Date");
        Comparator<Guest> comparator = Comparator.comparing(Guest::checkInDate);

        if (descending) {
            comparator = comparator.reversed();
        }

        return storage.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}