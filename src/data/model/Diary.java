package data.model;

import data.repositories.DiaryRepositoriesImplement;


import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private boolean isLocked = true;

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Diary(){

    }
//    @Override
//    public boolean equals(Object obj) {
//        if(obj instanceof Diary diary){
//            return diary.username.equals(username) && diary.password.equals(password);
//        }
//        return false;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getPassword() {
        return password;
    }
    public void setLock(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
