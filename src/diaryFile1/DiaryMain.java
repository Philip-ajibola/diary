package diaryFile1;

import diaryFile1.diaryController.DiaryController;
import diaryFile1.dtos.LoginRequest;
import diaryFile1.dtos.UpdateEntry;
import diaryFile1.dtos.entryCreation.EntryCreation;
import diaryFile1.dtos.request.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;
@SpringBootApplication
public class DiaryMain {

    public static void main(String[] args) {
        SpringApplication.run(DiaryMain.class,args);
    }

}
