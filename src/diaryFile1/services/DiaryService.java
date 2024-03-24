package diaryFile1.services;

import diaryFile1.data.model.Diary;
import diaryFile1.data.model.Entry;
import diaryFile1.dtos.UpdateEntry;
import diaryFile1.dtos.entryCreation.EntryCreation;
import diaryFile1.dtos.request.DeleteEntryRequest;
import diaryFile1.dtos.request.Request;

import java.util.List;

public interface DiaryService {
    void register(Request request);
    void login(String username, String password);

    long count();

    Diary findDiaryById(String username);

    void addEntry( EntryCreation entryCreation);

    void deleteEntry(DeleteEntryRequest entryRequest);

    Entry findEntryBy(String title, String username);

    void updateEntry(UpdateEntry updateEntry);

    void resetPassword(String password, String username, String newPassWord);
    void logOut(String username);

    List<Entry> findEntry(String username);

    List<Entry> findAllEntry();

    List<Diary> findAll();

    void deleteDiary(String username);
}
