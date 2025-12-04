package ua.hotel_managment;

import java.util.Objects;
import ua.util.Utils;
import ua.hotel_managment.enums.RoomStatus;

public class Room implements Comparable<Room> {

    private int roomNumber;
    private String type;
    private int capacity;
    private double price;
    private RoomStatus status;

    public Room(int roomNumber, String type, int capacity, double price, RoomStatus status) {
        try {
            setRoomNumber(roomNumber);
            setType(type);
            setCapacity(capacity);
            setPrice(price);
            setStatus(status);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid room's value. " + e);
        }
    }

    public Room(int roomNumber, String type, int capacity, double price) {
        this(roomNumber, type, capacity, price, RoomStatus.AVAILABLE);
    }

    public static Room create(int roomNumber, String type, int capacity, double price) {
        return new Room(roomNumber, type, capacity, price);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        if(!Utils.validatePositiveNumber(roomNumber)) {
            throw new IllegalArgumentException("Room number must be positive.");
        }
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(!Utils.validateObject(type) || !Utils.validateString(type)) {
            throw new NullPointerException("Type must be not empty.");
        }
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if(!Utils.validatePositiveNumber(capacity)) {
            throw new IllegalArgumentException("Capacity must be positive number");
        }
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(!Utils.validatePositiveNumber(price)) {
            throw new IllegalArgumentException("Price must be positive number.");
        }
        this.price = price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        if (status == null) {
            throw new NullPointerException("Room status cannot be null");
        }
        this.status = status;
    }

    @Override
    public int compareTo(Room other) {
        return Integer.compare(this.roomNumber, other.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, price, roomNumber, type, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room other = (Room) obj;
        return capacity == other.capacity
                && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
                && roomNumber == other.roomNumber
                && Objects.equals(type, other.type)
                && status == other.status;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", type=" + type
                + ", capacity=" + capacity + ", price=" + price
                + ", status=" + status + "]";
    }
}