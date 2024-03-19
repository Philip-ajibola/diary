package services;

import data.model.Diary;
import data.model.Entry;
import data.repositories.DiaryRepositories;
import data.repositories.DiaryRepositoriesImplement;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import exception.EntryNotFoundException;
import exception.InvalidPasswordException;
import exception.InvalidUserNameException;
import exception.UserNameExistException;

import java.util.List;

public class DiaryServiceImpl implements DiaryService {
    private DiaryRepositories diaryRepositories = new DiaryRepositoriesImplement();
    private  EntryService entryService = new EntryServiceImpl();

    @Override
    public void register(Request request) {
        if(isDiaryExisting(request.getUsername()))throw new UserNameExistException("User Name Existed Already");
        Diary diary = new Diary(request.getUsername(),request.getPassword());
        diaryRepositories.save(diary);
    }

    @Override
    public void login(String username, String password) {
        Diary diary  = findDiaryById(username);
        validateUsername(diary);
        validatePassword(password, diary);
        diary.unLock();
        diaryRepositories.save(diary);
    }

    private static void validatePassword(String password, Diary diary) {
        if(!diary.getPassword().equals(password))throw new InvalidPasswordException("Invalid Password ");
    }

    private  void validateUsername(Diary diary) {
        diary = findDiaryById(diary.getUsername());
        if(diary == null)throw new InvalidUserNameException("InValid UserName Provide A Valid Username");
    }

    private boolean isDiaryExisting(String username){
        for(Diary diary: diaryRepositories.findAll()){
            if(diary.getUsername().equals(username))return true;
        }
        return false;
    }
    @Override
    public long count() {
        return diaryRepositories.count();
    }

    @Override
    public Diary findDiaryById(String username) {
        return diaryRepositories.findById(username);
    }

    @Override
    public void addEntry(Diary diary, EntryCreation entryCreation) {
        Entry entry = new Entry();
        entry.setTitle(entryCreation.getTitle());
        entry.setBody(entryCreation.getBody());
        entry.setAuthor(diary.getUsername());
        entryService.create(entry);
    }

    @Override
    public void deleteAEntry(String username,String title) {
        entryService.deleteEntry(username,title);
    }

    @Override
    public Entry findEntryBy(String title, String username) {
        List<Entry> entries = entryService.findEntryOf(username);
        Entry expected = null;
        for(Entry entry: entries) if(entry.getTitle().equals(title)) expected = entry;
        validateEntry(expected);
        return expected;
    }

    private static void validateEntry(Entry expected) {
        if(expected == null) throw new EntryNotFoundException("Entry Not Found");
    }

    @Override
    public void updateEntry(String title, UpdateEntry updateEntry, String username) {
        List<Entry> entries = entryService.findEntryOf(username);
        Entry expected = null;
        for(Entry entry: entries) if(entry.getTitle().equals(title)) expected = entry;
        validateEntry(expected);
        expected.setTitle(updateEntry.getNewTitle());
        expected.setBody(updateEntry.getNewBody());
        entryService.update(expected);

    }

    @Override
    public void resetPassword(String password, String username, String newPassWord) {
        diaryRepositories.resetPassword(password,username,newPassWord);
        Diary diary = findDiaryById(username);
        diaryRepositories.save(diary);

    }


}
