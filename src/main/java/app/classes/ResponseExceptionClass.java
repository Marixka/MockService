package app.classes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static app.сontroller.RestControllers.LOGGER;

public class ResponseExceptionClass {

    public static ResponseEntity<?> validateErrorBody() {
        LOGGER.error("Отстуствует обязательный параметр. {}", HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body("Отстуствует обязательный параметр");
    }


}
