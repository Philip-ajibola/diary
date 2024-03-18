package services;

import data.model.Diary;
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
    public void deleteEntry(String author) {
       Entry entry = entryRepositories.findByAuthorName(author);
       entryRepositories.delete(entry);
    }
}
