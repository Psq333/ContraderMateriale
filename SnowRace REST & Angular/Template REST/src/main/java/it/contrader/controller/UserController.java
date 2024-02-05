package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends AbstractController<UserDTO>{
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
		return new ResponseEntity<>(userService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()), HttpStatus.OK);
	}

}