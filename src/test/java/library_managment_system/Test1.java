package test.patron;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import library_managment_system.LibraryManagmentSystemApplication;
import library_managment_system.models.Patron;
import java.time.LocalDateTime;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 



@SpringBootTest(classes = LibraryManagmentSystemApplication.class)
@AutoConfigureMockMvc
class Test1 {

	@Autowired
	private MockMvc mockMvc;


	@Test
	void CheckCreateNewPatron() throws Exception {
		Patron p = new Patron("Test", "User" , 10  ,"0000000", this.NewMailTimestamp() + "_justForTest@gmail.com");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(p);
        mockMvc.perform(post("/api/patrons").content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").exists())
        .andExpect(jsonPath("$.message" ,containsString("successfully")));
	}

	@Test
	void CheckCreateInvalidNewPatron() throws Exception {
		Patron p = new Patron("Test", "User" , 9  ,"test", "justForTestgmail.com");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(p);
        mockMvc.perform(post("/api/patrons").content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.errors").exists())
        .andExpect(jsonPath("$.errors.length()").value(3))
        .andExpect(status().isUnprocessableEntity());  
	}

	@Test
	void CheckExistsPatronInfo() throws Exception {
        mockMvc.perform(get("/api/patrons/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").exists())
        .andExpect(jsonPath("$.firstName" ,  notNullValue()))  
        .andExpect(jsonPath("$.firstName" , instanceOf(String.class)))  
		.andExpect(jsonPath("$.lastName").exists())
        .andExpect(jsonPath("$.lastName" ,  notNullValue()))  
        .andExpect(jsonPath("$.lastName" , instanceOf(String.class)))  
        .andExpect(jsonPath("$.age").exists())
        .andExpect(jsonPath("$.age").isNumber())
        .andExpect(jsonPath("$.age" ,greaterThan(9)))
		.andExpect(jsonPath("$.email").exists())
        .andExpect(jsonPath("$.email" , instanceOf(String.class)))  
        .andExpect(jsonPath("$.email" ,containsString("@")))
		.andExpect(jsonPath("$.phoneNumber").exists());
	}
    
    private String NewMailTimestamp(){
        LocalDateTime dateNow = LocalDateTime.now();
        String e0 = dateNow.toString();
        String e1 = e0.replaceAll("-", "");
        String e2 = e1.replaceAll(":", "");
        String newEmail = e2.replace(".", "");
        return newEmail;
    }
}
