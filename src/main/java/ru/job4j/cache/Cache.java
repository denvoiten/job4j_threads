package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base rsl = memory.computeIfPresent(model.getId(),
                (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    Base temp = new Base(key, model.getVersion() + 1);
                    temp.setName(model.getName());
                    return temp;
                });
        return rsl != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public boolean isEmpty() {
        return memory.isEmpty();
    }

    public Map<Integer, Base> getMemory() {
        return memory;
    }
}
