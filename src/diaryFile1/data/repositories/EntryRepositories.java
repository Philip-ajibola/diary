package diaryFile1.data.repositories;

import diaryFile1.data.model.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryRepositories extends MongoRepository<Entry,String> {
 Entry findEntryByTitle(String username);
}
