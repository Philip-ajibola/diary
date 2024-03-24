package diaryFile1.dtos.request;

import lombok.Data;

@Data
public class DeleteEntryRequest {
    private String username;
    private String title;
}
