package data.repositories;

import data.model.Entry;

import java.util.List;

public class EntryRepositoriesImplement implements EntryRepositories{
    private int id;

    @Override
    public Entry save(Entry entry) {
        return null;
    }

    @Override
    public List<Entry> findAll() {
        return null;
    }

    @Override
    public Entry findById(int Id) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(Entry entry) {

    }

    public int generateId() {
        return ++id;
    }
}
