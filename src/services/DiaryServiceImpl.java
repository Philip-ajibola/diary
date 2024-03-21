package services;

import data.model.Diary;
import data.model.Entry;
import data.repositories.DiaryRepositories;
import data.repositories.DiaryRepositoriesImplement;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import exception.*;

import java.util.List;

public class DiaryServiceImpl implements DiaryService {
    private static DiaryRepositories diaryRepositories = new DiaryRepositoriesImplement();
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
        diary.setLock(false);
        diaryRepositories.save(diary);
    }

    private static void validatePassword(String password, Diary diary) {
        if(!diary.getPassword().equals(password))throw new InvalidPasswordException("Invalid Password ");
    }

    private  void validateUsername(Diary diary) {
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
         Diary diary = diaryRepositories.findById(username);
         if(diary == null) throw new UserNotFoundException("User Not Found");
         return diary;
    }

    @Override
    public void addEntry(String username, EntryCreation entryCreation) {
        Entry entry = new Entry();
        extracted(username, entryCreation);
        entry.setTitle(entryCreation.getTitle());
        entry.setBody(entryCreation.getBody());
        entry.setAuthor(username);
        entryService.create(entry);
    }

    private void extracted(String username, EntryCreation entryCreation) {
        List<Entry> entries = findEntry(username);
        for(Entry entry1: entries) if(entry1.getTitle().trim().equals(entryCreation.getTitle().trim())) throw new EntryTitleExistException("Entry Title Existed Already ");
    }

    @Override
    public void deleteAEntry(String username,String title) {
       Entry entry =  findEntryBy(title,username);
       if(entry == null) throw new EntryNotFoundException("Entry not Found Exception");
       entryService.deleteEntry(username,title);
    }

    @Override
    public Entry findEntryBy(String title, String username) {
        List<Entry> entries = findEntry(username);
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
        List<Entry> entries = findEntry(username);
        Entry expected = null;
        for(Entry entry: entries) if(entry.getTitle().equals(title)) expected = entry;
        validateEntry(expected);
        expected.setTitle(updateEntry.getNewTitle());
        expected.setBody(updateEntry.getNewBody());
        entryService.create(expected);

    }

    @Override
    public void resetPassword(String password, String username, String newPassWord) {
        diaryRepositories.resetPassword(password,username,newPassWord);
        Diary diary = findDiaryById(username);
        diaryRepositories.save(diary);

    }

    @Override
    public void logOut(String username) {
        Diary diary = findDiaryById(username);
        diary.setLock(true);
    }
    @Override
    public List<Entry> findEntry(String username){
        Diary diary = findDiaryById(username);
        if(diary == null) throw new UserNotFoundException("User Not Found");
        return entryService.findEntryOf(username);
    }

    @Override
    public List<Entry> findAllEntry() {
        return entryService.findAll();
    }

    @Override
    public List<Diary> findAll() {
        return diaryRepositories.findAll();
    }

    @Override
    public void deleteDiary(String username) {
        diaryRepositories.delete(username);
    }

}
