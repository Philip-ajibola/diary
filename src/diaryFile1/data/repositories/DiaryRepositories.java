package diaryFile1.data.repositories;

import diaryFile1.data.model.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepositories extends MongoRepository<Diary,String> {

}
