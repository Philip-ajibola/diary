package data.repositories;

import data.model.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoriesImplement implements EntryRepositories{
    private int id;
    private List<Entry> entries = new ArrayList<>();

    @Override
    public Entry save(Entry entry) {
        if(entryIsNotPresent(entry)){
            entry.setId(generateId());
            System.out.println(entry.getId());
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

    private boolean entryIsNotPresent(Entry entry) {
        return entry.getId() == 0;
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
        return entries.size();
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

    public void updateId(int id) {

    }

    public void updateTitle(String newTitle) {

    }

    public void updateBody(String newBody) {
    }
}