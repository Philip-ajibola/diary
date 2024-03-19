package services;

import data.model.Diary;
import data.model.Entry;
import data.repositories.EntryRepositories;
import data.repositories.EntryRepositoriesImplement;

import java.util.ArrayList;
import java.util.List;

public class EntryServiceImpl implements EntryService{
    private static EntryRepositories entryRepositories = new EntryRepositoriesImplement();
    @Override
    public void create(Entry entry) {
        entryRepositories.save(entry);
    }
    @Override
    public void update(Entry entry) {
            entryRepositories.save(entry);
    }
    @Override
    public void deleteEntry(String author,String title) {
       List<Entry> entries = findEntryOf(author);
        Entry expected = null;
        entries.forEach(entry->{ if(entry.getTitle().equals(title)){
            entryRepositories.delete(entry);
       }});
    }

    @Override
    public int getNumberOfEntry() {
        return entryRepositories.findAll().size();
    }

    @Override
    public List<Entry> findEntryOf(String username) {
        List<Entry> found = new ArrayList<>();
        entryRepositories.findAll().forEach(entry ->{ if(entry.getAuthor().equals(username))found.add(entry);});
        return found;
    }

    @Override
    public List<Entry> findAll() {
        return entryRepositories.findAll();
    }
}
