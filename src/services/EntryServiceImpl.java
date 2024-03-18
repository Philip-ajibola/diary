package services;

import data.model.Entry;
import data.repositories.EntryRepositories;
import data.repositories.EntryRepositoriesImplement;

public class EntryServiceImpl implements EntryService{
    private EntryRepositories entryRepositories = new EntryRepositoriesImplement();
    @Override
    public void create(Entry entry) {
        entryRepositories.save(entry);
    }
    @Override
    public void update(Entry entry) {

    }
    @Override
    public void delete(Entry entry) {

    }
}
