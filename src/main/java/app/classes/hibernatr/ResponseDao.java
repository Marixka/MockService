package app.classes.hibernatr;

import java.util.List;

public interface ResponseDao {

    List<Response> findAll();
    Response findOption(String method, String optionId);

    void insertEmployee(Response emp);

    public void deleteEmployee(String emp, String method);
}
