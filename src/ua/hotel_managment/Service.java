package ua.hotel_managment;

import ua.util.Utils;

public record Service(String name, int price) {

    public Service {
        if (!Utils.validateObject(name) || !Utils.validateString(name)) {
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        if (!Utils.validatePositiveNumber(price)) {
            throw new IllegalArgumentException("Price cannot be negative or zero");
        }
    }

    public static Service create(String name, int price) {
        return new Service(name, price);
    }
}