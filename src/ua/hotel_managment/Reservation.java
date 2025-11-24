package ua.hotel_managment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ua.util.Utils;

/**
 * Represents a reservation in the hotel system.
 * A reservation contains information about the guest,
 * the room, optional services, and the start/end dates.
 */
public class Reservation {

    private Guest guest;
    private Room room;
    private List<Service> services;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs a new {@code Reservation}.
     * Validation is applied through setters.
     *
     * @param guest     the guest making the reservation
     * @param room      the reserved room
     * @param startDate the start date of the reservation
     * @param endDate   the end date of the reservation
     * @throws IllegalArgumentException if any value is invalid
     * @throws NullPointerException     if any value is {@code null}
     */
    public Reservation(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        try {
            setGuest(guest);
            setRoom(room);
            setStartDate(startDate);
            setEndDate(endDate);
            this.services = new ArrayList<>();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid reservation value. " + e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Invalid reservation value. " + e);
        }
    }

    /**
     * Factory method for creating a new {@code Reservation}.
     *
     * @param guest     the guest making the reservation
     * @param room      the reserved room
     * @param startDate the start date of the reservation
     * @param endDate   the end date of the reservation
     * @return a new {@code Reservation} instance
     * @throws IllegalArgumentException if any value is invalid
     * @throws NullPointerException     if any value is {@code null}
     */
    public static Reservation create(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        return new Reservation(guest, room, startDate, endDate);
    }

    /**
     * Returns the guest of this reservation.
     *
     * @return the guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * Sets the guest of this reservation.
     *
     * @param guest the guest
     * @throws NullPointerException if {@code guest} is {@code null}
     */
    public void setGuest(Guest guest) {
        if (!Utils.validateObject(guest)) {
            throw new NullPointerException("Guest can't be null.");
        }
        this.guest = guest;
    }

    /**
     * Returns the reserved room.
     *
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room for this reservation.
     *
     * @param room the reserved room
     * @throws NullPointerException if {@code room} is {@code null}
     */
    public void setRoom(Room room) {
        if (!Utils.validateObject(room)) {
            throw new NullPointerException("Room can't be null.");
        }
        this.room = room;
    }

    /**
     * Returns the start date of the reservation.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the reservation.
     *
     * @param startDate the reservation start date
     * @throws NullPointerException     if {@code startDate} is {@code null}
     * @throws IllegalArgumentException if {@code startDate} is in the past
     */
    public void setStartDate(LocalDate startDate) {
        if (!Utils.validateObject(startDate)) {
            throw new NullPointerException("Start date can't be null");
        }
        if (!Utils.validateDate(startDate, LocalDate.now())) {
            throw new IllegalArgumentException("Start date can't be in the past");
        }
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the reservation.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the reservation.
     *
     * @param endDate the reservation end date
     * @throws NullPointerException     if {@code endDate} is {@code null}
     * @throws IllegalArgumentException if {@code endDate} is before {@link #startDate}
     */
    public void setEndDate(LocalDate endDate) {
        if (!Utils.validateObject(endDate)) {
            throw new NullPointerException("End date can't be null");
        }
        if (!Utils.validateDate(endDate, startDate)) {
            throw new IllegalArgumentException("End date can't be before start date");
        }
        this.endDate = endDate;
    }

    /**
     * Returns the list of services associated with this reservation.
     *
     * @return a list of services
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     * Replaces the current list of services with a new one.
     *
     * @param services the new list of services
     * @throws NullPointerException if {@code services} is {@code null}
     */
    public void setServices(List<Service> services) {
        if (!Utils.validateObject(services)) {
            throw new NullPointerException("Services list can't be null");
        }
        this.services = services;
    }

    /**
     * Adds a single service to the list of services.
     *
     * @param service the service to add
     * @throws NullPointerException if {@code service} is {@code null}
     */
    public void addService(Service service) {
        if (!Utils.validateObject(service)) {
            throw new NullPointerException("Service can't be null");
        }
        this.services.add(service);
    }

    /**
     * Generates a hash code for the reservation.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(endDate, guest, room, startDate);
    }

    /**
     * Compares this reservation to another object for equality.
     * Two reservations are equal if they have the same guest, room,
     * start date, and end date.
     *
     * @param obj the object to compare
     * @return {@code true} if the reservations are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        Reservation other = (Reservation) obj;
        return Objects.equals(endDate, other.endDate)
                && Objects.equals(guest, other.guest)
                && Objects.equals(room, other.room)
                && Objects.equals(startDate, other.startDate);
    }

    /**
     * Returns a string representation of the reservation.
     *
     * @return formatted string with reservation details
     */
    @Override
    public String toString() {
        return "Reservation [guest=" + guest + ", room=" + room
                + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}