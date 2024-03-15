package data.model;

import data.repositories.DiaryRepositoriesImplement;

public class Diary {
    private String username;
    private String password;
    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Diary diary){
            return diary.username.equals(username) && diary.password.equals(password);
        }
        return false;
    }


    public String getUsername() {
        return username;
    }
}
