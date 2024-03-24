package diaryFile1.dtos;

import lombok.Data;

@Data
public class UpdateEntry {
    private String newTitle;
    private String newBody;
    private String existingTitle;
    private String username;
    }
