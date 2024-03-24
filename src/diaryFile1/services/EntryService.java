package diaryFile1.services;

import diaryFile1.data.model.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntryService {
    void create(Entry entry);

    void deleteEntry(String Author,String title);

    int getNumberOfEntry();
    List<Entry> findEntryOf(String username);

    List<Entry> findAll();
}
