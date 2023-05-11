package app.classes.hibernatr;

import java.util.List;

public interface ResponseService {
    List<Response> findAll();

    Response findOption(String method, String optionId);

    void insertEmployee(Response emp);

    void deleteEmployee(String emp, String method);

}