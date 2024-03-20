package diaryController;

import data.model.Diary;
import data.model.Entry;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import org.junit.jupiter.api.AfterEach;
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
    @AfterEach
  public void clearEntryList(){
  diaryService.findAllEntry().clear();
  diaryController.findAll().clear();

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
        String expected1 = diaryController.createEntry(entryCreation,"username");
        assertEquals("Entry Successfully Added To list Of Entry",expected1);

    }
    @Test
    public void testUserThatDoesNotExistCantCreateEntry(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
         assertEquals("User Not Found",diaryController.createEntry(entryCreation,"wrongUsername"));

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
        String expected1 = diaryController.createEntry(entryCreation,"username");
        assertEquals("Entry Successfully Added To list Of Entry",expected1);
        assertEquals("Entry Title Existed Already ",diaryController.createEntry(entryCreation1,"username"));
    }
    @Test
    public void testThatUserCanDeleteEntry(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById("username");
        diaryController.createEntry(entryCreation,"username");
        assertEquals("Entry Deleted Successfully",diaryController.deleteEntry("username","title"));
    }
    @Test
    public void testThatWhenUserTryToDeleteEntryThatDoesExist_errorMessageIsThrown(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        assertEquals("Entry Not Found",diaryController.deleteEntry("username","title"));
    }
    @Test
    public void testThatEntryCantBeDeletedWithWrongUserName(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
        diaryController.createEntry(entryCreation,"username");
        assertEquals("User Not Found",diaryController.deleteEntry("wrongUsername","title"));

    }
    @Test
    public void testThatEntryCanBeUpdated(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
        diaryController.createEntry(entryCreation,"username");
        UpdateEntry updateEntry= new UpdateEntry("newTitle","newBody");
        assertEquals("Entry Updated",diaryController.updateEntry("title",updateEntry,"username"));
    }
    @Test
    public void testThatEntryThatDoesNotExistCantBeUpdated(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
        diaryController.createEntry(entryCreation,"username");
        UpdateEntry updateEntry= new UpdateEntry("newTitle","newBody");
        assertEquals("Entry Updated",diaryController.updateEntry("title",updateEntry,"username"));
        assertEquals("Entry Not Found",diaryController.updateEntry("title",updateEntry,"username"));
    }
    @Test
    public void testThatEntryCantBeUpdatedWithWrongUserName(){
        DiaryController diaryController = new DiaryController();
        Request request = new Request("username","password");
        String expected = diaryController.createDiary(request);
        assertEquals("Diary Successfully Created ",expected);

        EntryCreation entryCreation = new EntryCreation("title","body");
        diaryController.createEntry(entryCreation,"username");
        UpdateEntry updateEntry= new UpdateEntry("newTitle","newBody");
        assertEquals("User Not Found",diaryController.updateEntry("title",updateEntry,"WrongUsername"));

    }

}