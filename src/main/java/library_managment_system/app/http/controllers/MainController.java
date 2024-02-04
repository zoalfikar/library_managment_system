package library_managment_system.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static library_managment_system.controllers.helper.IndexController.sendSuccessMessage;


@RestController	

public class MainController {
	@GetMapping(path="/")
	public @ResponseBody Object  greeting() {
		return sendSuccessMessage("Welcome in library managment system");
	}
}


