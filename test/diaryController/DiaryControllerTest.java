package diaryController;

import data.model.Diary;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class DiaryControllerTest {
    private DiaryController diaryController;
    private DiaryService diaryService;
    @BeforeEach
    public void initializer(){
        diaryController = new DiaryController();
        diaryService = new DiaryServiceImpl();
    }
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
    @Test
    public void testUserCanCreateEntryToDiary(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById("username");
        String expected1 = diaryController.createEntry(entryCreation,diary);
        assertEquals("Entry Successfully Added To list Of Entry",expected1);
    }
    @Test
    public void testEntryWithSameEntryTitleCantBeCreated_ErrorMessageIsThrown(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
        EntryCreation entryCreation1 = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById("username");
        String expected1 = diaryController.createEntry(entryCreation,diary);
        assertEquals("Entry Successfully Added To list Of Entry",expected1);
        assertEquals("Entry Title Existed Already ",diaryController.createEntry(entryCreation1,diary));


    }

}