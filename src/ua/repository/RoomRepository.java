package ua.repository;

import ua.hotel_managment.Room;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomRepository extends GenericRepository<Room> {

    public RoomRepository() {
        super(r -> String.valueOf(r.getRoomNumber()));
    }

    public List<Room> sortByPrice() {
        LOGGER.info("Sorting rooms by Price");
        return storage.values().stream()
                .sorted(Comparator.comparingDouble(Room::getPrice))
                .collect(Collectors.toList());
    }

    public List<Room> sortByCapacityDesc() {
        LOGGER.info("Sorting rooms by Capacity (DESC)");
        return storage.values().stream()
                .sorted(Comparator.comparingInt(Room::getCapacity).reversed())
                .collect(Collectors.toList());
    }
}