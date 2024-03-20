package diaryController;

import dtos.request.Request;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryControllerTest {
    @Test
    public void testThatUserCanCreateDiary(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);
    }
    @Test
    public void testThatWhenUserTryToRegisterWithExistingUsername_ErrorMessageIsShown(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        Request request1 = new Request("username","password");
        String expected1 = diaryController.createDiary(request1);
        assertEquals("User Name Existed Already",expected1);
    }

}