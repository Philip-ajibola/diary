package diaryFile1.diaryController;

import diaryFile1.data.model.Diary;
import diaryFile1.data.model.Entry;
import diaryFile1.dtos.LoginRequest;
import diaryFile1.dtos.UpdateEntry;
import diaryFile1.dtos.entryCreation.EntryCreation;
import diaryFile1.dtos.request.DeleteEntryRequest;
import diaryFile1.dtos.request.Request;
import diaryFile1.exception.DiaryException;
import diaryFile1.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class DiaryController {
    @Autowired
    private  DiaryService diaryService;
    @PostMapping("/sign-up")
    public String createDiary(@RequestBody Request request) {
        try {
            diaryService.register(request);
            return "\n Diary Registered Successfully ";
        }catch (DiaryException e){
            return e.getMessage();
            //throw new DiaryException(e.getMessage());
        }
    }

    @PostMapping("/create-entry")
    public String createEntry(@RequestBody EntryCreation entryCreation) {
        try{
        diaryService.addEntry(entryCreation);
        return "\nEntry Successfully Added To list Of Entry";
        }catch(DiaryException e){
            return String.format("\n%s",e.getMessage());
        }
    }
    @PostMapping("/log-in")
    public String login(@RequestBody LoginRequest loginRequest){
        try {
            diaryService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return "\nLogin SuccessFul";
        }catch (DiaryException e){
            return e.getMessage();
           // throw new DiaryException( e.getMessage());
        }
    }

    @DeleteMapping("/delete-entry")
    public String deleteEntry(@RequestBody DeleteEntryRequest entryRequest) {
        try {
            diaryService.deleteEntry(entryRequest);
            return "\nEntry Deleted Successfully";
        }catch(DiaryException e){
            return  String.format("\n%s",e.getMessage());
        }
    }
    @PostMapping("/update-entry")
    public String updateEntry(@RequestBody UpdateEntry updateEntry) {
        try {
            diaryService.updateEntry(updateEntry);
            return "\nEntry Updated";
        }catch (DiaryException e){
            return String.format("\n%s",e.getMessage());
        }
    }@GetMapping("/find-all")
    public List<Diary> findAll(){
        return diaryService.findAll();
    }

    @PatchMapping("/log-out/{username}")
    public String logOut(@PathVariable("username") String username) {
        try {
            diaryService.logOut(username);
            return "\n" +username + " Logged Out";
        }catch(DiaryException e){
            return String.format("\n%s",e.getMessage());
        }
    }
    @DeleteMapping("/delete-diary/{username}")
    public String deleteDiary(@PathVariable("username") String username) {
        try {
            diaryService.deleteDiary(username);
            return "\nDiary Has Been Deleted";
        }catch(DiaryException e){
            return String.format("\n%s", e.getMessage());
        }
    }

    @PatchMapping("/reset-password")
    public String resetPassword(@RequestBody String password, String username, String newPassword) {
        try {
            diaryService.resetPassword(password, username, newPassword);
            return "Password Reset";
        }catch(DiaryException e){
            return String.format("%s",e.getMessage());
        }
    }
    @GetMapping("/find-all-entry/{username}")
    public List<Entry> findDiaryEntryBy(@PathVariable("username") String username) {
        return diaryService.findEntry(username);
    }

    @GetMapping("/find-entry")
    public String findEntry(@RequestBody String username,String title){
        try {
            return String.format("%s", diaryService.findEntryBy(title, username));
        }catch (DiaryException e){
            return String.format(e.getMessage());
        }
    }

}
