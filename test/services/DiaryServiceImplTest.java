package services;
import data.model.Diary;
import data.model.Entry;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import exception.*;

import dtos.request.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImplTest {
    private DiaryService diaryService;
    private EntryService entryService;
    @BeforeEach
    public void createDiaryService(){
        diaryService  = new DiaryServiceImpl();
        entryService = new EntryServiceImpl();
    }
    @AfterEach
    public void emptyDairy(){
        entryService.findAll().clear();
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
    @AfterEach
    public void clearDiary(){
        diaryService.findAll().clear();
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
        assertThrows(UserNotFoundException.class,()->diaryService.login("wrongUsername", "password"));
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
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        assertEquals(1,entryService.getNumberOfEntry());

    }

    @Test
    public void testThatMultipleEntryCanBeCreated(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        assertEquals(1,entryService.getNumberOfEntry());

        EntryCreation entryCreation1 = new EntryCreation("title1","body");
        diaryService.addEntry(request.getUsername(),entryCreation1);
        assertEquals(2,entryService.getNumberOfEntry());    }

    @Test
    public void testThatEntryInADiaryCanBeUpDated(){
        Request request  = new Request("username","password");
        Request request1 = new Request("username1","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        diaryService.register(request1);
        diaryService.login(request1.getUsername(), request1.getPassword());

        EntryCreation entryCreation = new EntryCreation("title","body");
        diaryService.addEntry(request.getUsername(),entryCreation);
        diaryService.addEntry(request1.getUsername(),entryCreation);
        diaryService.deleteAEntry("username","title");
        assertEquals(1,entryService.getNumberOfEntry());

    }
    @Test
    public void testThatUserCanFindEntry(){
        Request request  = new Request("username","password");
        Request request1 = new Request("username1","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        diaryService.register(request1);
        diaryService.login(request1.getUsername(), request1.getPassword());

        EntryCreation entryCreation = new EntryCreation("title","body");
        diaryService.addEntry(request.getUsername(),entryCreation);
        diaryService.addEntry(request1.getUsername(),entryCreation);
        diaryService.deleteAEntry("username","title");
        assertEquals(1,entryService.getNumberOfEntry());
    }
    @Test
    public void testThatMultipleEntryCanBeAddedByOneUser(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        EntryCreation entryCreation1 = new EntryCreation("title1","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        diaryService.addEntry(request.getUsername(),entryCreation1);
        assertEquals(2,entryService.findEntryOf("username").size());
    }
    @Test
    public void testThatWhenEntryIsCreatedEntryCanBeDeleted(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title1","body");
        EntryCreation entryCreation1 = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        diaryService.addEntry(request.getUsername(),entryCreation1);
        diaryService.deleteAEntry("username","title");
        assertEquals(1,entryService.findEntryOf("username").size());
    }
    @Test
    public void testThatEntryCanBeDeleted(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        assertEquals(diaryService.findEntryBy("title","username"),entryService.findEntryOf("username").get(0));
    }
    @Test
    public void testThatWhenEntryIsNotFoundExceptionIsThrown(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        assertThrows(EntryNotFoundException.class,()->diaryService.findEntryBy("title1","username"));
    }
    @Test
    public void testThatWhenUserNameIsNotValidWhenFindingEntry_EntryIsNotFoundExceptionIsThrown(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        assertThrows(EntryNotFoundException.class,()->diaryService.findEntryBy("title","wrongUsername"));
    }
    @Test
    public void testThatEntryCanBeUpdateInADiary(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        UpdateEntry updateEntry = new UpdateEntry("newTitle","newBody");
        diaryService.updateEntry("title",updateEntry,"username");
        Entry entry = diaryService.findEntryBy("newTitle","username");
        assertThrows(EntryNotFoundException.class,()->diaryService.findEntryBy("title","wrongUsername"));
    }
    @Test
    public void testThatWhenEntryIsNotFoundEntryCantBeUpdated(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        UpdateEntry updateEntry = new UpdateEntry("newTitle","newBody");
        assertThrows(EntryNotFoundException.class,()->diaryService.updateEntry("title1",updateEntry,"username"));
    }
        @Test
    public void testThatWhenEntryIsNotFoundEntryCantBeUpdatedWithWrongUserName(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        UpdateEntry updateEntry = new UpdateEntry("newTitle","newBody");
        assertThrows(DiaryException.class,()->diaryService.updateEntry("title",updateEntry,"wrongUsername"));
    }
    @Test
    public void testThatUserCanResetDairyPassword(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        diaryService.resetPassword("password","username","newPassword");
        diary = diaryService.findDiaryById("username");

        assertEquals(diary.getPassword(),"newPassword");
    }
    @Test
    public void testThatUserCanLogOut(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        assertFalse(diaryService.findDiaryById("username").isLocked());
        diaryService.logOut("username");
        assertTrue(diaryService.findDiaryById("username").isLocked());
    }
    @Test
    public void testThatWhenDiaryHasAnEntry_AnotherEntryCantBeCreatedWithSameTitle(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        EntryCreation entryCreation = new EntryCreation("title","body");
        EntryCreation entryCreation1 = new EntryCreation("title","body");
        Diary diary = diaryService.findDiaryById(request.getUsername());
        diaryService.addEntry(request.getUsername(),entryCreation);
        assertThrows(EntryTitleExistException.class,()->diaryService.addEntry(request.getUsername(),entryCreation1));

    }
    @Test
    public void testThatDiaryCanBeDeleted(){
        Request request  = new Request("username","password");
        diaryService.register(request);
        diaryService.login(request.getUsername(), request.getPassword());
        diaryService.deleteDiary("username");
        assertEquals(0,diaryService.count());
    }

}