package diaryFile1.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@Document
public class Entry {

    private  String author;
    private  String title;
    private  String body;
    @Id
    private String id;
    private LocalDate localDate = LocalDate.now();

    @Override
    public String toString() {
        return String.format("""
                Entry Author: %s                   Date Of Creation: %s
                
                            Entry Title: %s
                            
                   Entry Body:
                         %s  \n       
                """,author,localDate,title,body);
    }
}
