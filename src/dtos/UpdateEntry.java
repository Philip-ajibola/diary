package dtos;

public class UpdateEntry {
    private String newTitle;
    private String newBody;
    public UpdateEntry(String newTitle,String newBody){
            this.newTitle = newTitle;
            this.newBody = newBody;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewBody() {
        return newBody;
    }

    public void setNewBody(String newBody) {
        this.newBody = newBody;
    }
}
