package data.repositories;

import data.model.Diary;

import java.util.List;

public interface DiaryRepositories {
    Diary save(Diary diary);
    List<Diary> findAll();
    Diary findById(String username);
    long count();
    void delete(String id);
    void delete(Diary diary);
}
