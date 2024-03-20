package services;

import data.model.Diary;
import data.model.Entry;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;

import java.util.Collection;
import java.util.List;

public interface DiaryService {
    void register(Request request);
    void login(String username, String password);

    long count();

    Diary findDiaryById(String username);

    void addEntry(String username, EntryCreation entryCreation);

    void deleteAEntry(String username,String title);

    Entry findEntryBy(String title, String username);

    void updateEntry(String title, UpdateEntry updateEntry, String username);

    void resetPassword(String password, String username, String newPassWord);
    void logOut(String username);

    List<Entry> findEntry(String username);

    List<Entry> findAllEntry();

    List<Diary> findAll();

    void deleteDiary(String username);
}
