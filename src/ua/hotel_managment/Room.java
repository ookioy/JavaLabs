package ua.hotel_managment;

import java.util.Objects;

import ua.util.Utils;

/**
 * Represents a hotel room with details such as room number, type of room, capacity and price.
 * Provides validation for input data through setters.
 */
public class Room {

    private int roomNumber;
    private String type;
    private int capacity; // maximal count of people in the room
    private double price;


    /**
     * Constructor. Sets values to fields of class Room.
     *
     * @param roomNumber	room's number	(not negative number or 0)
     * @param type			room's type		(not empty or null)
     * @param capacity		room's capacity	(number bigger or equal to 1)
     * @param price			room's price	(not negative number or 0)
     * @throws IllegalArgumentException if fields have invalid values
     */
    public Room(int roomNumber, String type, int capacity, double price) {
        try {
            setRoomNumber(roomNumber);
            setType(type);
            setCapacity(capacity);
            setPrice(price);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid room's value. " + e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Invalid room's value. " + e);
        }
    }

    /**
     * Factory method. Creates an object of class Room.
     *
     * @param roomNumber	room's number	(not negative number or 0)
     * @param type			room's type		(not empty or null)
     * @param capacity		room's capacity	(number bigger or equal to 1)
     * @param price			room's price	(not negative number or 0)
     * @throws IllegalArgumentException if fields have invalid values
     */
    public static Room create(int roomNumber, String type, int capacity, double price) {
        return new Room(roomNumber, type, capacity, price);
    }

    /**
     * Returns number of the room.
     *
     * @return roomNumber room's number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets value of room's number.
     *
     * @param roomNumber room's number
     * @throws IllegalArgumentException if the room's number is not positive.
     */
    public void setRoomNumber(int roomNumber) {
        if(!Utils.validatePositiveNumber(roomNumber)) {
            throw new IllegalArgumentException("Room number must be positive.");
        }
        this.roomNumber = roomNumber;
    }

    /**
     * Returns type of the room.
     *
     * @return type room's type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets value of room's type.
     *
     * @param type room's type
     * @throws NullPointerException if the room's type is empty.
     */
    public void setType(String type) {
        if(!Utils.validateObject(type) || !Utils.validateString(type)) {
            throw new NullPointerException("Type must be not empty.");
        }
        this.type = type;
    }

    /**
     * Returns maximum count of people in the room.
     *
     * @return capacity room's capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets value of room's capacity.
     *
     * @param capacity room's capacity
     * @throws IllegalArgumentException if the room's capacity is not positive.
     */
    public void setCapacity(int capacity) {
        if(!Utils.validatePositiveNumber(capacity)) {
            throw new IllegalArgumentException("Capacity must be positive number");
        }
        this.capacity = capacity;
    }

    /**
     * Returns price of the room.
     *
     * @return price room's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets value of room's price.
     *
     * @param price room's price
     * @throws IllegalArgumentException if the room's price is not positive.
     */
    public void setPrice(double price) {
        if(!Utils.validatePositiveNumber(price)) {
            throw new IllegalArgumentException("Price must be positive number.");
        }
        this.price = price;
    }

    /**
     * Returns a hash code value for the Room object.
     * This method is consistent with equals(), using all fields of class.
     *
     * @return hash code of all fields
     */
    @Override
    public int hashCode() {
        return Objects.hash(capacity, price, roomNumber, type);
    }

    /**
     * Compares this Room to another object for equality.
     * Two Room objects are considered equal if they have the same fields.
     *
     * @param obj the object to compare with
     * @return true if the given object is a Room with the same fields, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Room other = (Room) obj;
        return capacity == other.capacity && Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
                && roomNumber == other.roomNumber && Objects.equals(type, other.type);
    }

    /**
     * Returns a string representation of the Room object.
     * Includes the guest's roomNumber, type, capacity and price.
     *
     * @return a formatted string with the room's details
     */
    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", type=" + type + ", capacity=" + capacity + ", price=" + price
                + "]";
    }


}