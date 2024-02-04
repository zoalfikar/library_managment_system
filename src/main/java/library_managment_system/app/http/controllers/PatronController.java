package library_managment_system.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import library_managment_system.repositories.PatronRepository;
import library_managment_system.models.Patron;
import library_managment_system.app.http.requests.PatronValidator;
import library_managment_system.app.exceptions.NoSuchPatronExistsException;
import org.springframework.transaction.annotation.Transactional;

import static library_managment_system.controllers.helper.IndexController.sendSuccessMessage;


@RestController	
@RequestMapping(path="api/patrons")
public class PatronController {
	@Autowired 
	private PatronRepository patronRepository;

	@Transactional
	@PostMapping(path="") 
	public @ResponseBody Object addNewPatron (@Valid @RequestBody PatronValidator patron ) {
		Patron newPatron = new Patron();
		newPatron.setAge(patron.getAge());
        newPatron.setEmail(patron.getEmail());
        newPatron.setFirstName(patron.getFirstName());
        newPatron.setLastName(patron.getLastName());
        newPatron.setPhoneNumber(patron.getPhoneNumber());
		patronRepository.save(newPatron);
		return sendSuccessMessage("Patron information have been saved successfully!");
	}

	@GetMapping(path="/{id}") 
	public @ResponseBody Object getPatron (@PathVariable("id") long id) {
		Patron patron =  patronRepository.findById(id).orElseThrow(() -> new NoSuchPatronExistsException(id));
		return patron;
	}

	@Transactional
	@PutMapping(path="/{id}") 
	public @ResponseBody Object updatePatron (@PathVariable("id") long id , @Valid @RequestBody PatronValidator patron) {
		Patron updatedPatron =  patronRepository.findById(id).orElseThrow(() -> new NoSuchPatronExistsException(id));
        updatedPatron.setAge(patron.getAge());
        updatedPatron.setEmail(patron.getEmail());
        updatedPatron.setFirstName(patron.getFirstName());
        updatedPatron.setLastName(patron.getLastName());
        updatedPatron.setPhoneNumber(patron.getPhoneNumber());
		patronRepository.save(updatedPatron);
		return sendSuccessMessage("Patron information has been updated successfully!");
	}


	@Transactional
	@DeleteMapping(path="/{id}") 
	public @ResponseBody Object deletePatron (@PathVariable("id") long id) {
		Patron patron =  patronRepository.findById(id).orElseThrow(() -> new NoSuchPatronExistsException(id));
		patronRepository.delete(patron);
		return sendSuccessMessage("Patron has been deleted successfully!");
	}

	@GetMapping(path="")
	public @ResponseBody Iterable<Patron> getAllPatrons() {
		return patronRepository.findAll();
	}

}