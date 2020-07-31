package ru.job4j.multithreading.non_blocking_algoritm;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {
    private final ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    public void update(Base model) throws OptimisticException {
        cache.computeIfPresent(model.getId(), (k, v) -> {
                    if (cache.get(model.getId()).getVersion() == model.getVersion() - 1) {
                        model.setVersion(v.getVersion() + 1);
                        System.out.println("version updated");
                        return model;
                    } else {
                        throw new OptimisticException("incorrect model version");
                    }
                }
        );
    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }

    public ConcurrentHashMap<Integer, Base> getCache() {
        return cache;
    }
}
