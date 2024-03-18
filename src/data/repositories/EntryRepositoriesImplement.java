package data.repositories;

import data.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoriesImplement implements EntryRepositories{
    private int id;
    private List<Entry> entries = new ArrayList<>();

    @Override
    public Entry save(Entry entry) {
        if(isNewEntry(entry)){
            entry.setId(generateId());
            id=entry.getId();
            entries.add(entry);
        }else{
            for(Entry entry1: entries){
                if(entry1.getId() == entry.getId()){
                    entries.remove(entry1);
                    entries.add(entry);
                }

            }
        }

        return entry;
    }

    private boolean isNewEntry(Entry entry) {
        return entry.getId() == 0 || isEntryPresent(entry);
    }
    private boolean isEntryPresent(Entry entry1){
        for(Entry entry: entries){
            if(entry.getId() == entry1.getId()){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Entry> findAll() {
        return entries;
    }

    @Override
    public Entry findById(int Id) {
        for(Entry entry1: entries) {
            if (entry1.getId() == id) {
                return entry1;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public void delete(int id) {
        entries.removeIf(entry -> entry.getId() == id);
    }

    @Override
    public void delete(Entry entry) {
        entries.removeIf(entry1-> entry1 == entry);
    }

    public int generateId() {
        return ++id;
    }


}
