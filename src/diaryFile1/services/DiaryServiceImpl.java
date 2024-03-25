package diaryFile1.services;

import diaryFile1.data.model.Diary;
import diaryFile1.data.model.Entry;
import diaryFile1.data.repositories.DiaryRepositories;
import diaryFile1.dtos.request.DeleteEntryRequest;
import diaryFile1.exception.*;
import diaryFile1.dtos.UpdateEntry;
import diaryFile1.dtos.entryCreation.EntryCreation;
import diaryFile1.dtos.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private  DiaryRepositories diaryRepositories ;
    @Autowired
    private  EntryService entryService ;

    @Override
    public void register(Request request) {
        isDiaryInList(request.getUsername());
        Diary diary = new Diary();
        diary.setUsername(request.getUsername());
        diary.setPassword(request.getPassword());
        diary.setLocked(false);
        diaryRepositories.save(diary);
    }

    @Override
    public void login(String username, String password) {
        Diary diary  = findDiaryById(username);
        System.out.println(diary.getPassword());
        validateUsername(diary);
        validatePassword(password, diary);
        diary.setLocked(false);
        diaryRepositories.save(diary);
    }

    private static void validatePassword(String password, Diary diary) {
        if(!diary.getPassword().equals(password))throw new InvalidPasswordException("Invalid Password ");
    }

    private  void validateUsername(Diary diary) {
        if(diary == null)throw new InvalidUserNameException("InValid UserName Provide A Valid Username");
    }

    private void isDiaryInList(String username){
        for(Diary diary: diaryRepositories.findAll()){
            if(diary.getUsername().equals(username))throw new UserNameExistException("User name already exist");}

    }
    @Override
    public long count() {
        return diaryRepositories.count();
    }

    @Override
    public Diary findDiaryById(String username) {
        Optional<Diary> diary = diaryRepositories.findById(username);
        if(diary.isEmpty()) throw new UserNotFoundException("User Not Found");
        return diary.get();
    }

    @Override
    public void addEntry( EntryCreation entryCreation) {
        isLoggedIn(entryCreation.getAuthor());
        validateEntryTitle(entryCreation.getAuthor(),entryCreation.getTitle());
        Entry entry = new Entry();
        entry.setTitle(entryCreation.getTitle());
        entry.setBody(entryCreation.getBody());
        entry.setAuthor(entryCreation.getAuthor());
        entryService.create(entry);
    }



    private void validateEntryTitle(String username,String title) {
        Diary diary = findDiaryById(username);
        if(diary==null) throw new UserNotFoundException("User Not Found");
        List<Entry> entries = findEntry(username);
        for(Entry entry1: entries) if(entry1.getTitle().trim().equals(title.trim())) throw new EntryTitleExistException("Entry Title Existed Already ");
    }

    @Override
    public void deleteEntry(DeleteEntryRequest entryRequest) {
        isLoggedIn(entryRequest.getUsername());
        Entry entry =  getEntry(entryRequest.getTitle(),entryRequest.getUsername());
        validateEntry(entry);
        entryService.deleteEntry(entryRequest.getUsername(),entryRequest.getTitle());
    }

    @Override
    public Entry findEntryBy(String title, String username) {
        return getEntry(title, username);
    }

    private static void validateEntry(Entry expected) {
        if(expected == null) throw new EntryNotFoundException("Entry Not Found");
    }

    @Override
    public void updateEntry(UpdateEntry updateEntry) {
        isLoggedIn(updateEntry.getUsername());
        Entry expected = getEntry(updateEntry.getExistingTitle(), updateEntry.getUsername());
        validateEntry(expected);
        expected.setAuthor(updateEntry.getUsername());
        expected.setTitle(updateEntry.getNewTitle());
        expected.setBody(updateEntry.getNewBody());
        entryService.create(expected);

    }

    private void isLoggedIn(String username) {
        if(findDiaryById(username).isLocked())throw new UserNotFoundException("You are not logged in\n please login");
    }

    private Entry getEntry(String title, String username) {
        List<Entry> entries = findEntry(username);
        Entry expected = null;
        for(Entry entry: entries) if(entry.getTitle().equals(title)){ expected = entry;}
        if(expected == null) throw new EntryNotFoundException("Entry not found");
        return expected;
    }

    @Override
    public void resetPassword(String password, String username, String newPassWord) {
        Diary diary = findDiaryById(username);
        if (!diary.getPassword().equals(password))throw new InvalidPasswordException("Invalid Password");
        diary.setPassword(newPassWord);
        diaryRepositories.save(diary);

    }

    @Override
    public void logOut(String username) {
        Diary diary = findDiaryById(username);
        diary.setLocked(true);
        diaryRepositories.save(diary);
    }
    @Override
    public List<Entry> findEntry(String username){
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
        Diary diary = findDiaryById(username);
        if(diary== null)throw new UserNotFoundException("User Not found");
        entryService.deleteEntryOf(username);
        diaryRepositories.delete(diary);
    }

}