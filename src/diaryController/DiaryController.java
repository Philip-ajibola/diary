package diaryController;

import data.model.Diary;
import data.model.Entry;
import dtos.LoginRequest;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import exception.DiaryException;
import services.DiaryService;
import services.DiaryServiceImpl;

import java.util.List;

public class DiaryController {
    private  DiaryService diaryService = new DiaryServiceImpl();

    public String createDiary(Request request) {
        try {
            diaryService.register(request);
            return "\n Diary Registered Successfully ";
        }catch (DiaryException e){
            throw new DiaryException(e.getMessage());
        }
    }

    public String createEntry(EntryCreation entryCreation, String username) {
        try{
        diaryService.addEntry(username,entryCreation);
        return "\nEntry Successfully Added To list Of Entry";
        }catch(DiaryException e){
            return String.format("\n%s",e.getMessage());
        }
    }
    public String login(LoginRequest loginRequest){
        try {
            diaryService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return "\nLogin SuccessFul";
        }catch (DiaryException e){
            throw new DiaryException( e.getMessage());
        }
    }


    public String deleteEntry(String username, String title) {
        try {
            diaryService.deleteAEntry(username,title);
            return "\nEntry Deleted Successfully";
        }catch(DiaryException e){
            return  String.format("\n%s",e.getMessage());
        }
    }

    public String updateEntry(String title, UpdateEntry updateEntry, String body) {
        try {
            diaryService.updateEntry(title, updateEntry, body);
            return "\nEntry Updated";
        }catch (DiaryException e){
            return String.format("\n%s",e.getMessage());
        }
    }
    public List<Diary> findAll(){
        return diaryService.findAll();
    }

    public String logOut(String username) {
        try {
            diaryService.logOut(username);
            return "\n" +username + " Logged Out";
        }catch(DiaryException e){
            return String.format("\n%s",e.getMessage());
        }
    }
    public String deleteDiary(String username) {
        try {
            diaryService.deleteDiary(username);
            return "\nDiary Has Been Deleted";
        }catch(DiaryException e){
            return String.format("\n%s", e.getMessage());
        }
    }


    public String resetPassword(String password, String username, String newPassword) {
        try {
            diaryService.resetPassword(password, username, newPassword);
            return "Password Reset";
        }catch(DiaryException e){
            return String.format("%s",e.getMessage());
        }
    }

    public List<Entry> findDiaryEntryBy(String username) {
        return diaryService.findEntry(username);
    }

    public String findEntry(String username,String title){
        try {
            return String.format("%s", diaryService.findEntryBy(title, username));
        }catch (DiaryException e){
            return String.format(e.getMessage());
        }
    }

}
