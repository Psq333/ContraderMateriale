package it.contrader.authenticationservice.controller;

import it.contrader.authenticationservice.customException.EmailAlreadyInUseException;
import it.contrader.authenticationservice.customException.UsernameAlreadyInUseException;
import it.contrader.authenticationservice.dto.LoginDTO;
import it.contrader.authenticationservice.dto.MessageResponse;
import it.contrader.authenticationservice.dto.SignupDTO;
import it.contrader.authenticationservice.dto.UserDTO;
import it.contrader.authenticationservice.security.JwtUtils;
import it.contrader.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok()
            .body(userService.login(loginDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signUpRequest) {
        try {
            userService.registerUser(signUpRequest);
            return ResponseEntity.ok().body(userService.login(new LoginDTO(signUpRequest.getUsername(), signUpRequest.getPassword())));
        } catch (UsernameAlreadyInUseException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/read")
    public ResponseEntity<?> read(@RequestParam("id") Long id){
        return ResponseEntity.ok()
                .body(userService.read(id));
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<?> getAllPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok()
                .body(userService.getAllPage(pageNumber, pageSize));
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody SignupDTO userToUpDTO){
        try {
            return ResponseEntity.ok().body(userService.update(userToUpDTO, id));
        } catch (UsernameAlreadyInUseException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody SignupDTO userToUpDTO){
        try {
            userService.insert(userToUpDTO);
            return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
        } catch (UsernameAlreadyInUseException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam("id") Long id){
            userService.delete(id);
            return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }


//    @GetMapping("/findByUsername")
//    public ResponseEntity<?> findByUsername(@RequestParam("username") String username){
//        return ResponseEntity.ok()
//                .body(userService.findByUsername(username));
//    }

    @GetMapping("/readusername")
    public ResponseEntity<?> readusername(@RequestParam("username") String username){
        return ResponseEntity.ok()
                .body(userService.findByUsername(username));
    }

    @GetMapping("/getAllAdmin")
    public List<UserDTO> getAllAdmin(){
        return userService.findAllAdmin();
    }

    @GetMapping("/findByUsername")
    public UserDTO findByUsername(@RequestParam("username") String username){
        return userService.findByUsername(username);
    }

}
