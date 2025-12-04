package ua.repository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GenericRepository<T> {
    protected static final Logger LOGGER = Logger.getLogger(GenericRepository.class.getName());
    protected final Map<String, T> storage = new HashMap<>();
    protected final IdentityExtractor<T> extractor;

    public GenericRepository(IdentityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public void add(T item) {
        String id = extractor.extract(item);
        if (storage.containsKey(id)) {
            LOGGER.warning("Duplicate item found with ID: " + id + ". Item was not added.");
        } else {
            storage.put(id, item);
            LOGGER.info("Item added with ID: " + id);
        }
    }

    public void remove(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            LOGGER.info("Item removed with ID: " + id);
        } else {
            LOGGER.warning("Attempted to remove non-existent item with ID: " + id);
        }
    }

    public T findByIdentity(String id) {
        T item = storage.get(id);
        if (item != null) {
            LOGGER.info("Item found with ID: " + id);
        } else {
            LOGGER.warning("Item not found with ID: " + id);
        }
        return item;
    }

    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    public List<T> sortByIdentity(String order) {
        Comparator<T> comparator = Comparator.comparing(extractor::extract);

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        LOGGER.info("Sorting by Identity (" + order + ")");
        return storage.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}