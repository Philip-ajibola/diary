package services;
import data.model.Diary;
import dtos.entryCreation.EntryCreation;
import exception.InvalidPasswordException;
import exception.InvalidUserNameException;
import exception.UserNameExistException;

import dtos.request.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImplTest {
    private DiaryService diaryService;
    @BeforeEach
    public void createDiaryService(){
        diaryService  = new DiaryServiceImpl();
    }

    @Test
    public void testThatUserCanRegister(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        assertEquals(1,diaryService.count());
    }
    @Test
    public void testThatMultipleUsersCanRegister(){
        Request request  = new Request("username","password");
        Request request1 = new Request("username1","password");
        diaryService.register(request);
        diaryService.register(request1);
        assertEquals(2,diaryService.count());
    }
    @Test
    public void testThatUserNameThatExistCantBeRegisteredAgain(){
        Request request  = new Request("username","password");
        Request request1 = new Request("username","password");
        diaryService.register(request);
        assertThrows(UserNameExistException.class,()->diaryService.register(request1));
        assertEquals(1,diaryService.count());
    }
    @Test
    public void testThatUserCanLoginToDiaryApp(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        assertFalse(diaryService.findDiaryById(request.getUsername()).isLocked());
    }
    @Test
    public void testThatUserCantLoginToDiaryAppWithWrongUserName(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        assertThrows(InvalidUserNameException.class,()->diaryService.login("wrongUsername", "password"));
        assertTrue(diaryService.findDiaryById(request.getUsername()).isLocked());
    }
    @Test
    public void testThatUserCantLoginToDiaryAppWithWrongPassword(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        assertThrows(InvalidPasswordException.class,()->diaryService.login("username","wrongPassword"));
        assertTrue(diaryService.findDiaryById(request.getUsername()).isLocked());
    }
    @Test
    public void testThatEntryCanBeAddedToADiary(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(diary,entryCreation);
        assertEquals(1,diary.getNumberOfEntries());
    }
    @Test
    public void testThatMultipleEntryCanBeCreated(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(diary,entryCreation);
        assertEquals(1,diary.getNumberOfEntries());

        EntryCreation entryCreation1 = new EntryCreation("title","body");
        Diary diary1 = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(diary1,entryCreation);
        assertEquals(2,diary.getNumberOfEntries());    }

    @Test
    public void testThatEntryInADiaryCanBeUpDated(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(diary,entryCreation);
        diaryService.deleteAEntry(diary,1);
        assertEquals(0,diary.getNumberOfEntries());

    }

}