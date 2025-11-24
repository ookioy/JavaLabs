package ua.hotel_managment;

import java.util.Objects;
import ua.util.Utils;

/**
 * Represents an additional service in the hotel,
 * such as cleaning, breakfast, or spa access.
 * Each service has a name and a price.
 */
public class Service {

    /** Name of the service (e.g., "Breakfast", "Cleaning") */
    private String name;

    /** Price of the service in chosen currency units */
    private int price;

    /**
     * Constructs a Service object with the given name and price.
     * Validation is performed using setters.
     *
     * @param name  the name of the service
     * @param price the price of the service
     * @throws IllegalArgumentException if the provided values are invalid
     */
    public Service(String name, int price) {
        try {
            setName(name);
            setPrice(price);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid service's value. " + e);
        } catch (NullPointerException e) {
            throw new NullPointerException("Invalid service's value. " + e);
        }
    }

    /**
     * Factory method for creating a new Service object.
     *
     * @param name  the name of the service
     * @param price the price of the service
     * @return a new Service instance
     */
    public static Service create(String name, int price) {
        return new Service(name, price);
    }

    /**
     * Returns the name of the service.
     *
     * @return the service name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the service.
     *
     * @param name the new service name
     * @throws NullPointerException if the name is null, empty, or whitespace
     */
    public void setName(String name) {
        if (!Utils.validateObject(name) || !Utils.validateString(name)) {
            throw new NullPointerException("Name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Returns the price of the service.
     *
     * @return the service price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the service.
     *
     * @param price the new service price
     * @throws IllegalArgumentException if the price is negative
     */
    public void setPrice(int price) {
        if (Utils.validateNegativeNumber(price)) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        this.price = price;
    }

    /**
     * Generates a hash code for the service object.
     * Based on the service name and price.
     *
     * @return hash code of the service
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    /**
     * Compares this service to another object for equality.
     * Two services are considered equal if they have the same name and price.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Service other = (Service) obj;
        return Objects.equals(name, other.name) && price == other.price;
    }

    /**
     * Returns a string representation of the service object,
     * including its name and price.
     *
     * @return formatted string with service details
     */
    @Override
    public String toString() {
        return "Service [name=" + name + ", price=" + price + "]";
    }
}