package diaryController;

import data.model.Diary;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import exception.DiaryException;
import services.DiaryService;
import services.DiaryServiceImpl;

public class DiaryController {
    private DiaryService diaryService = new DiaryServiceImpl();

    public String createDiary(Request request) {
        try {
            diaryService.register(request);
            return "Diary Successfully Created ";
        }catch (Exception e){
            return String.format("%s",e.getMessage());
        }
    }

    public String createEntry(EntryCreation entryCreation, Diary diary) {
        try{
        diaryService.addEntry(diary,entryCreation);
        return "Entry Successfully Added To list Of Entry";
        }catch(DiaryException e){
            return String.format("%s",e.getMessage());
        }
    }


}
