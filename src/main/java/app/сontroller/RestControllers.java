package app.сontroller;

import app.classes.hibernatr.Response;
import app.classes.hibernatr.ResponseService;
import com.github.dockerjava.api.exception.NotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static app.classes.ResponseExceptionClass.validateErrorBody;


//        LOGGER.trace("This is a syslog message at TRACE level.");
//        LOGGER.debug("This is a syslog message at DEBUG level.");
//        LOGGER.info("Request GET /getMethod/{}" , optionId);
//        LOGGER.warn("This is a syslog message at WARN level.");
//        LOGGER.error("This is a syslog message at ERROR level.");

@RestController
@Validated
@RequiredArgsConstructor
public class RestControllers {
    @Resource
    ResponseService employeeService;
    public static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @RequestMapping(value = "/getMethod/{abonent}",
            method = RequestMethod.GET)
    public String getMethod(@PathVariable (name = "abonent") String abonent) {
        LOGGER.info("Request GET /getMethod/{}" , abonent);
        var response = "";
        try {
            response = employeeService.findOption("getMethod", abonent).getResponse();
            LOGGER.info("Response GET /getMethod/{} , body: {}" , abonent, response);
        }
        catch (EmptyResultDataAccessException e)
        {
            LOGGER.warn("Response GET /getMethod/{} ," , e.getMessage());
        }
        return  response;
    }



    // api для БД
    @GetMapping(value = "/respList")
    public List<Response> getEmployees() {
        LOGGER.info("Request GET /respList");
        var response = employeeService.findAll();
        LOGGER.info("Response GET /respList , body: " + gson.toJson(response));
        return response;
    }

    @PostMapping(value = "/createResp")
    public ResponseEntity<?> createEmployees(@RequestBody Response emp) throws NotFoundException {
        LOGGER.info("Request POST /createResp , body: " + gson.toJson(emp));
        //валидация обязательных параметров
        if ((emp.getResponse()==null) || (emp.getAbonent()==null) || (emp.getMethodname()==null))
            return validateErrorBody();

        employeeService.deleteEmployee(emp.getAbonent(), emp.getMethodname());
        employeeService.insertEmployee(emp);
        LOGGER.info("Response POST /createResp. {}" , HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/deleteRespByAbonent/{abonent}/{method}",
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRespByAbonent(@PathVariable (name = "abonent") String abonent,
                                    @PathVariable (name = "method") String method) {
        LOGGER.info("Request DELETE /deleteRespByAbonent/{}/{}" , abonent, method);

        if ((abonent==null) || (method==null))
            return validateErrorBody();
        employeeService.deleteEmployee(abonent, method);
        return ResponseEntity.status(HttpStatus.OK).build();
    }






}
