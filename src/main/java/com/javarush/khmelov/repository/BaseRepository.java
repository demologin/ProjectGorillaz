package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.AbstractEntity;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseRepository<T extends AbstractEntity> implements Repository<T> {

    protected final Map<Long, T> map = new ConcurrentHashMap<>();

    public final AtomicLong id = new AtomicLong(0L);

    @Override
    public Collection<T> getAll() {
        return map.values();
    }


    @Override
    public T get(long id) {
        return map.get(id);
    }

    @Override
    public void create(T entity) {
        entity.setId(id.incrementAndGet());
        update(entity);
    }

    @Override
    public void update(T entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        map.remove(entity.getId());
    }

    protected boolean nullOrEquals(Object patternField, Object repoField) {
        return patternField == null || patternField.equals(repoField);
    }
}
