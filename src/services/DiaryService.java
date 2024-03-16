package services;

import data.model.Diary;
import data.model.Entry;
import dto.request.Request;

import java.util.concurrent.locks.ReentrantLock;

public interface DiaryService {
    void register(Request request);
    void login(String username, String password);

    long count();

    Diary findDiaryById(String username);

    void addEntry(Entry entry);
}
