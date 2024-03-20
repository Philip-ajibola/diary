package diaryController;

import dtos.request.Request;
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
}
