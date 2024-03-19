package services;

import data.model.Diary;
import data.model.Entry;
import data.repositories.DiaryRepositories;
import data.repositories.DiaryRepositoriesImplement;
import data.repositories.EntryRepositories;
import data.repositories.EntryRepositoriesImplement;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import exception.DiaryNotFound;
import exception.InvalidPasswordException;
import exception.InvalidUserNameException;
import exception.UserNameExistException;

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
    }

    private static void validatePassword(String password, Diary diary) {
        if(!diary.getPassword().equals(password))throw new InvalidPasswordException("Invalid Password ");
    }

    private  void validateUsername(Diary diary) {
        if(diary ==null)throw new InvalidUserNameException("InValid UserName Provide A Valid Username");
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


}
