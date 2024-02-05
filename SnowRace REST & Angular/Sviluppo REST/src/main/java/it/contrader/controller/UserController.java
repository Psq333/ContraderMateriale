package it.contrader.controller;

import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends AbstractController<UserDTO>{
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
		UserDTO user = userService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("getAllPaginata")
	public ResponseEntity<Page<UserDTO>> getall(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber ) {
		return new ResponseEntity<>(userService.getAllPaginata(PageRequest.of(pageNumber, pageSize)), HttpStatus.OK);
	}

	@GetMapping("/getAllAdmin")
	public ResponseEntity<List<UserDTO>> getallAdmin(){
		return new ResponseEntity<>(userService.getAll(User.Usertype.ADMIN),HttpStatus.OK);
	}

	@GetMapping("/readusername")
	public UserDTO readusername(String username) {
		return userService.readusername(username);
	}



	@DeleteMapping("/deleteUser")
	public void deleteUser(@RequestParam("id") long id) {
		userService.deleteUser(id);
	}


}