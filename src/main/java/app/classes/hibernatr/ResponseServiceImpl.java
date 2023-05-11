package app.classes.hibernatr;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {
    @Resource
    ResponseDao responseDao;
    @Override
    public List<Response> findAll() {
        return responseDao.findAll();
    }

    @Override
    public Response findOption(String method, String optionId)
    {
        return responseDao.findOption(method, optionId);
    }

    @Override
    public void insertEmployee(Response emp) {
        responseDao.insertEmployee(emp);

    }

    @Override
    public void deleteEmployee(String emp, String method) {
        responseDao.deleteEmployee(emp, method);

    }
}