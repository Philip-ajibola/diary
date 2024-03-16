package services;
import exception.InvalidPasswordException;
import exception.InvalidUserNameException;
import exception.UserNameExistException;

import dto.request.Request;
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


}