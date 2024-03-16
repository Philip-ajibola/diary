package services;

import dto.request.Request;

public interface DiaryService {
    void register(Request request);
    void login(String username, String password);

    long count();
}
