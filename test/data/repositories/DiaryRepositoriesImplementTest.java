package data.repositories;

import data.model.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoriesImplementTest {
    private  DiaryRepositoriesImplement diaryRepositoriesImpl;
    @BeforeEach
    public void createDairy(){
         diaryRepositoriesImpl = new DiaryRepositoriesImplement();
    }
    @Test
    public void testThatDairyCanBeSaved(){
        Diary diary = new Diary("username","password");
        diaryRepositoriesImpl.save(diary);
        assertEquals(1l,diaryRepositoriesImpl.count());
    }
    @Test
    public void testThatDiaryCanAddMoreDiaries(){
        Diary diary1 = new Diary("username","password");
        Diary diary2 = new Diary("username1","password");
        diaryRepositoriesImpl.save(diary1);
        diaryRepositoriesImpl.save(diary2);
        assertEquals(2l,diaryRepositoriesImpl.count());

    }
    @Test
    public void testThatDiaryCanBeDeletedById(){
        Diary diary1 = new Diary("username","password");
        Diary diary2 = new Diary("username1","password");
        diaryRepositoriesImpl.save(diary1);
        diaryRepositoriesImpl.save(diary2);
        assertEquals(2l,diaryRepositoriesImpl.count());
        diaryRepositoriesImpl.delete("username");
        assertEquals(1l,diaryRepositoriesImpl.count());

    }
    @Test
    public void testThatDiaryCanBeDeletedMultipleTimeById(){
        Diary diary1 = new Diary("username","password");
        Diary diary2 = new Diary("username1","password");
        diaryRepositoriesImpl.save(diary1);
        diaryRepositoriesImpl.save(diary2);
        assertEquals(2l,diaryRepositoriesImpl.count());
        diaryRepositoriesImpl.delete("username");
        diaryRepositoriesImpl.delete("username1");
        assertEquals(0l,diaryRepositoriesImpl.count());

    }
    @Test
    public void testThatDiaryCanBeFindWithUserName(){
        Diary diary1 = new Diary("username","password");
        Diary diary2 = new Diary("username1","password");
        diaryRepositoriesImpl.save(diary1);
        diaryRepositoriesImpl.save(diary2);
        Diary diary = diaryRepositoriesImpl.findById("username");
        assertEquals(diary,diary1);
    }
    @Test
    public void testThatDairyThatDoesNotExistCantBeFound(){
        Diary diary1 = new Diary("username","password");
        assertNull(diaryRepositoriesImpl.findById("usename1"));
    }
    @Test
    public void testThatDairyCanBeDeletedByDiaryObject(){
        Diary diary1 = new Diary("username","password");
        Diary diary2 = new Diary("username1","password");
        diaryRepositoriesImpl.save(diary1);
        diaryRepositoriesImpl.save(diary2);
        diaryRepositoriesImpl.delete(diary1);
        Diary diary = diaryRepositoriesImpl.findById("username");
        assertNull(diary);
        assertEquals(1l,diaryRepositoriesImpl.count());
    }
    @Test
    public void testThatAllDiaryCanBeFound(){
        Diary diary1 = new Diary("username","password");
        Diary diary2 = new Diary("username1","password");
        diaryRepositoriesImpl.save(diary1);
        diaryRepositoriesImpl.save(diary2);
        assertEquals(2l,diaryRepositoriesImpl.findAll().size());
    }


}