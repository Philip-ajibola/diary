package data.model;

public class Entry {

    private  String title;
    private  String body;
    private int id;
    public Entry(int id,String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
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
}
