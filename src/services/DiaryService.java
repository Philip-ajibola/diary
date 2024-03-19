package services;

import data.model.Diary;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;

public interface DiaryService {
    void register(Request request);
    void login(String username, String password);

    long count();

    Diary findDiaryById(String username);

    void addEntry(Diary diary, EntryCreation entryCreation);

    void deleteAEntry(String username,String title);

    void findEntryBy(String title, String username);
}
