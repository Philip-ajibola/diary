package diaryController;

import data.model.Diary;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import exception.DiaryException;
import services.DiaryService;
import services.DiaryServiceImpl;

import java.util.List;

public class DiaryController {
    private  DiaryService diaryService = new DiaryServiceImpl();

    public String createDiary(Request request) {
        try {
            diaryService.register(request);
            return "Diary Successfully Created ";
        }catch (Exception e){
            return String.format("%s",e.getMessage());
        }
    }

    public String createEntry(EntryCreation entryCreation, String username) {
        try{
        diaryService.addEntry(username,entryCreation);
        return "Entry Successfully Added To list Of Entry";
        }catch(DiaryException e){
            return String.format("%s",e.getMessage());
        }
    }


    public String deleteEntry(String username, String title) {
        try {
            diaryService.deleteAEntry(username,title);
            return "Entry Deleted Successfully";
        }catch(DiaryException e){
            return String.format("%s",e.getMessage());
        }
    }

    public String updateEntry(String title, UpdateEntry updateEntry, String body) {
        try {
            diaryService.updateEntry(title, updateEntry, body);
            return "Entry Updated";
        }catch (DiaryException e){
            return String.format("%s",e.getMessage());
        }
    }
    public List<Diary> findAll(){
        return diaryService.findAll();
    }

    public void logOut(String username) {
        diaryService.logOut(username);
    }
}
