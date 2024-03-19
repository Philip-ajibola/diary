package data.repositories;

import data.model.Diary;
import exception.InvalidPasswordException;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoriesImplement implements DiaryRepositories{
    private List<Diary> diaries =new ArrayList<>();


    @Override
    public Diary save(Diary diary) {
        if( validateDiary(diary)) {
            for (Diary diary1 : diaries)
                if (diary1.getUsername().equals(diary.getUsername())) {
                    diary1.setUsername(diary.getUsername());
                    diary1.setPassword(diary.getPassword());
                }
        }
        else diaries.add(diary);
        return diary;
    }

    private boolean validateDiary(Diary diary) {
        for(Diary diary1: diaries)if(diary1.getUsername().equals(diary.getUsername()))return true;
        return false;
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public Diary findById(String username) {
        for(Diary diary: diaries){
            if(diary.getUsername().equals(username)){
                return diary;
            }
        }
        return null;
    }

    @Override
    public long count() {
        return  diaries.size();
    }

    @Override
    public void delete(String id) {
        for(Diary diary: diaries){
            if(diary.getUsername().equals(id)){
                diaries.remove(diary);
                break;
            }
        }
    }

    @Override
    public void delete(Diary diary) {
        for(Diary diary1: diaries){
            if(diary1.equals(diary)){
                diaries.remove(diary);
                break;
            }
        }
    }

    @Override
    public void resetPassword(String password, String username, String newPassword) {
        Diary diary = findById(username);
        if(!diary.getPassword().equals(password))throw new InvalidPasswordException("Invalid Password");
        for(Diary diary1: diaries){
            if(diary1 == diary) diary1.setPassword(newPassword);
        }
    }

}
