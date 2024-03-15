package data.model;

public class Diary {
    private String username;
    private String password;
    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}
