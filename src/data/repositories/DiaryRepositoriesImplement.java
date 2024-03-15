package data.repositories;

import data.model.Diary;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoriesImplement implements DiaryRepositories{
    private List<Diary> diaries =new ArrayList<>();


    @Override
    public Diary save(Diary diary) {
        diaries.add(diary);
        return diary;
    }

    @Override
    public List<Diary> findAll() {
        return null;
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
        return diaries.size();
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

    }
}
