package ua.hotel_managment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ua.util.Utils;
import ua.hotel_managment.enums.ReservationStatus;

public class Reservation {

    private Guest guest;
    private Room room;
    private List<Service> services;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    public Reservation(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        try {
            setGuest(guest);
            setRoom(room);
            setStartDate(startDate);
            setEndDate(endDate);
            this.services = new ArrayList<>();
            this.status = ReservationStatus.CONFIRMED;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid reservation value. " + e);
        }
    }

    public static Reservation create(Guest guest, Room room, LocalDate startDate, LocalDate endDate) {
        return new Reservation(guest, room, startDate, endDate);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        if (!Utils.validateObject(guest)) {
            throw new NullPointerException("Guest can't be null.");
        }
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        if (!Utils.validateObject(room)) {
            throw new NullPointerException("Room can't be null.");
        }
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (!Utils.validateObject(startDate)) {
            throw new NullPointerException("Start date can't be null");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (!Utils.validateObject(endDate)) {
            throw new NullPointerException("End date can't be null");
        }
        if (!Utils.validateDate(endDate, startDate)) {
            throw new IllegalArgumentException("End date can't be before start date");
        }
        this.endDate = endDate;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        if (!Utils.validateObject(services)) {
            throw new NullPointerException("Services list can't be null");
        }
        this.services = services;
    }

    public void addService(Service service) {
        if (!Utils.validateObject(service)) {
            throw new NullPointerException("Service can't be null");
        }
        this.services.add(service);
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        if (status == null) {
            throw new NullPointerException("Status cannot be null");
        }
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, guest, room, startDate, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reservation other = (Reservation) obj;
        return Objects.equals(endDate, other.endDate)
                && Objects.equals(guest, other.guest)
                && Objects.equals(room, other.room)
                && Objects.equals(startDate, other.startDate)
                && status == other.status;
    }

    @Override
    public String toString() {
        return "Reservation [guest=" + guest + ", status=" + status + ", room=" + room
                + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}