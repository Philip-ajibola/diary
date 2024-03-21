package data.model;

import java.time.LocalDate;

public class Entry {

    private  String author;
    private  String title;
    private  String body;
    private int id;
    private LocalDate localDate;
    public Entry(int id,String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
        this.localDate = LocalDate.now();
    }

    public Entry(){}
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getBody(){
        return body;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("""
                Entry Author: %s                   Date Of Creation: %s
                
                            Entry Title: %s
                            
                   Entry Body:
                         %s         
                """,author,localDate,title,body);
    }
}
