import data.model.Diary;
import diaryController.DiaryController;
import dtos.LoginRequest;
import dtos.UpdateEntry;
import dtos.entryCreation.EntryCreation;
import dtos.request.Request;
import exception.DiaryException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DiaryMain {
    private  static DiaryController diaryController = new DiaryController();
    private static Scanner scanner = new Scanner(System.in);


    private static void mainMenu(){
        try{
        int userInput = Integer.parseInt(input("""
               <<<<<<<<< Welcome To GossipVille Diary >>>>>>>>>>
                    
                        [<1>] Sign up             [<2>] sign in
                """));

        switch(userInput){
            case 1-> registerUser();
            case 2-> login();
        }
        } catch(InputMismatchException e){
            print(e.getMessage());
        }
    }

    private static void registerUser() {
        String userName = input("Enter Your firstName ");
        if(userName.matches("[a-zA-Z]+"))throw new IllegalArgumentException("Invalid Input Supplied");
        String password = input("Enter Password ");
        print(diaryController.createDiary(new Request(userName,password)));
    }
    private static void login(){
        String username = input("Enter Your Username");
        String password = input("Enter Your password");
        print(diaryController.login(new LoginRequest(username,password)));
        displayMenu(username);
    }

    private static void displayMenu(String username) {
        try {
            int userResponse = Integer.parseInt(input("""
                    <><><><><><><><><><><><> WELCOME TO HOME GOSSIPVILLE <><><><><><><><><><><><><>
                                    
                               [<1>] ADD ENTRY                         [<2>] UPDATE ENTRY
                               
                               [<3>] DELETE ENTRY                      [<3>] RESET PASSWORD
                               
                               [<5>] FIND ENTRY                         [<6>] LOGOUT 
                                    
                                                  [<7>] DELETE ACCOUNT
                    """));
            collectUserResponse(username, userResponse);
        }catch(Exception e){
            print(e.getMessage());
            mainMenu();
        }
    }

    private static void collectUserResponse(String username, int userResponse) {
        switch (userResponse){
            case 1-> addEntry(username);
            case 2-> updateEntry(username);
            case 3-> deleteEntry(username);
            case 4-> resetPassword(username);
            case 5-> findEntry(username);
            case 6-> logout(username);
            case 7-> deleteAccount(username);
        }
    }

    private static void findEntry(String username) {
        String entryTitle = input("Enter Entry Title");
        diaryController.findEntry(username,entryTitle);

    }

    private static void resetPassword(String username) {
        String oldPassword = input("Enter Your Old Password");
        String newPassword = input("Enter your new password");
        print(diaryController.resetPassword(oldPassword,username,newPassword));
    }

    private static void deleteEntry(String username) {
        String entryTitle = input("Enter The Entry Title You Want To Delete ");
        print(diaryController.deleteEntry(username,entryTitle));
        displayMenu(username);
    }

    private static void updateEntry(String username) {
        String oldTitle = input("Enter the Title Of the Entry You Want to Update");
        String newTitle = input("Enter new title (re-enter the old one if you don't want to change the title)");
        String newBody = input("Enter the new body ");
        print(diaryController.updateEntry(oldTitle,new UpdateEntry(newTitle,newBody),username));
        displayMenu(username);
    }

    private static void addEntry(String username) {
        try {
            String title = input("Enter Entry title");
            String body = input("Enter Entry Body ");
            print(diaryController.createEntry(new EntryCreation(title,body),username));

        }catch(DiaryException | InputMismatchException e){
            print(e.getMessage());
        }finally {
            displayMenu(username);
        }
    }

    private static void print(String diary) {
        System.out.println(diary);
    }


    private static String input(String s) {
        System.out.println(s);
        return scanner.next();
    }
}
