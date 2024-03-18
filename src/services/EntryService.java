package services;

import data.model.Diary;
import data.model.Entry;

public interface EntryService {
    void create(Entry entry);
    void update(Entry entry);
    void deleteEntry(String Author);
}
