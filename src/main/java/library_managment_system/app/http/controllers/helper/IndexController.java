package library_managment_system.controllers.helper;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ResponseBody;


public class IndexController {
    public static  Object sendSuccessMessage(String msg) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", msg);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
