package services;

import data.model.Entry;

import java.util.List;

public interface EntryService {
    void create(Entry entry);
    void update(Entry entry);
    void deleteEntry(String Author,String title);

    int getNumberOfEntry();
    List<Entry> findEntryOf(String username);

    List<Entry> findAll();
}
