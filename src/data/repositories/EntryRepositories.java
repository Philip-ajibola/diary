package data.repositories;

import data.model.Entry;

import java.util.List;

public interface EntryRepositories {
    Entry save();
    List<Entry> findAll();
    Entry findById(int Id);
    long count();
    void delete(int id);
    void delete(Entry entry);


}
