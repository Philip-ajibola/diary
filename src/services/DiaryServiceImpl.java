package services;

import data.model.Diary;
import data.repositories.DiaryRepositories;
import data.repositories.DiaryRepositoriesImplement;
import dto.request.Request;
import exception.UserNameExistException;
import services.DiaryService;

public class DiaryServiceImpl implements DiaryService {
    private DiaryRepositories diaryRepositories = new DiaryRepositoriesImplement();

    @Override
    public void register(Request request) {
        if(isDiaryExisting(request.getUsername()))throw new UserNameExistException("User Name Existed Already");
        Diary diary = new Diary(request.getUsername(),request.getPassword());
        diaryRepositories.save(diary);
    }

    @Override
    public void login(String username, String password) {

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
}
