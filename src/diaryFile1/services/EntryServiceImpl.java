package diaryFile1.services;

import diaryFile1.data.model.Entry;
import diaryFile1.data.repositories.EntryRepositories;
import diaryFile1.exception.EntryNotFoundException;
import diaryFile1.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService{
    @Autowired
    private   EntryRepositories entryRepositories ;
    @Override
    public void create(Entry entry) {
        entryRepositories.save(entry);
    }
    @Override
    public void deleteEntry(String author,String title) {
        Entry entry = entryRepositories.findEntryByTitle(title);
        if(!entryRepositories.existsById(entry.getId()))throw new EntryNotFoundException("Entry not found");
        entryRepositories.deleteById(entry.getId());
    }

    @Override
    public int getNumberOfEntry() {
        return entryRepositories.findAll().size();
    }

    @Override
    public List<Entry> findEntryOf(String username) {
        List<Entry> found = new ArrayList<>();
        findAll().forEach(entry ->{ if(entry.getAuthor() != null && entry.getAuthor().equals(username))found.add(entry);});
        return found;
    }

    @Override
    public List<Entry> findAll() {
        return entryRepositories.findAll();
    }

    @Override
    public void deleteEntryOf(String username) {
        findAll().forEach(entry ->{ if(entry.getAuthor() != null && entry.getAuthor().equals(username))findAll().remove(entry);});
    }
}