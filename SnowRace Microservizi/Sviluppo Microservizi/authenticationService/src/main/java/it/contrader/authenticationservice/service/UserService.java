package it.contrader.authenticationservice.service;

import feign.FeignException;
import it.contrader.authenticationservice.customException.CustomFeignException;
import it.contrader.authenticationservice.customException.UserNotFoundException;
import it.contrader.authenticationservice.customException.UsernameAlreadyInUseException;
import it.contrader.authenticationservice.dto.LoggedUserDTO;
import it.contrader.authenticationservice.dto.LoginDTO;
import it.contrader.authenticationservice.dto.SignupDTO;
import it.contrader.authenticationservice.dto.UserDTO;
import it.contrader.authenticationservice.feignClient.AnagraficaFeignClient;
import it.contrader.authenticationservice.mapper.UserMapper;
import it.contrader.authenticationservice.model.Role;
import it.contrader.authenticationservice.model.User;
import it.contrader.authenticationservice.repository.RoleRepository;
import it.contrader.authenticationservice.repository.UserRepository;
import it.contrader.authenticationservice.security.JwtUtils;
import it.contrader.authenticationservice.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AnagraficaFeignClient anagraficaFeignClient;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager; //interfaccia di Spring security per usarla però la annotiamo come bean in WebSecurityConfig.java

    @Autowired
    private UserDetailsService userDetailsService;

    public LoggedUserDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager //effettuiamo l'autenticazione tramite l'ogg authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
                                //è un'implementazione di authenticationManager tramite una nuova istanza con username e pw, i parametri che gli passiamo sono definiti in UsernamePasswordAuthenticationToken.class di Spring security
                                // il nome utente e la password sono forniti dall'utente tramite l'oggetto loginDTO
                                //il metodo .authenticate() viene chiamato sull'oggetto authenticationManager passando il UsernamePasswordAuthenticationToken creato. Il AuthenticationManager sarà responsabile di autenticare l'utente utilizzando le credenziali fornite.
                                //l'oggetto authentication restituirà l'esito dell'autenticazione.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwt(userDetails);

        String role = (String)(userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList()).toArray()[0];
        role = role.toLowerCase().substring(5);
        return new LoggedUserDTO(userDetails.getId(),
            userDetails.getUsername(),
            jwt,
            role);
    }

    public void registerUser(SignupDTO signUpRequest) throws UsernameAlreadyInUseException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyInUseException("Error: Username is already taken!");
        }

        Set<String> roleToBUpd = new HashSet<>();
        roleToBUpd.add(signUpRequest.getUsertype());

        User savedUser = userRepository.save(User.builder()
            .username(signUpRequest.getUsername())
            .password(encoder.encode(signUpRequest.getPassword()))
            .roles(authService.createRoles(roleToBUpd))
            .build());

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(savedUser.getUsername());

        jwtUtils.setRequestJwt(jwtUtils.generateJwt(userDetails));

//        try {
//            signUpRequest.getAnagrafica().setUserId(savedUser.getId());
//            anagraficaFeignClient.register(signUpRequest.getAnagrafica());
//        } catch (FeignException e) {
//            throw new CustomFeignException("Errore durante la registrazione dell'anagrafica", e);
//        }

    }

    public UserDTO read(Long id) {
        return userMapper.toUserDTO(userRepository.findById(id).orElse(null));
    }

    public Page<UserDTO> getAllPage(int pageNumber, int pageSize){
        return userMapper.toUserDTOPage(userMapper.toUserDTOList(userRepository.findAll()), pageNumber, pageSize);
    }

    public List<UserDTO> getAll(){
        return userMapper.toUserDTOList(userRepository.findAll());
    }

    public UserDTO insert(SignupDTO insertDTO) throws UsernameAlreadyInUseException{
        if (userRepository.existsByUsername(insertDTO.getUsername())) {
            throw new UsernameAlreadyInUseException("Error: Username is already taken!");
        }
        String encPass = null;
        if (insertDTO.getPassword() != null && !insertDTO.getPassword().isEmpty()) {
            encPass = (encoder.encode(insertDTO.getPassword()));
        }
        Set<Role> insRoles = null;
        Set<String> roleToBIns = new HashSet<>();
        if (insertDTO.getUsertype() != null && !insertDTO.getUsertype().isEmpty()) {
            roleToBIns.add(insertDTO.getUsertype());
            insRoles = (authService.createRoles(roleToBIns));
        }
        User ins = User.builder()
                .username(insertDTO.getUsername())
                .password(encPass)
                .roles(insRoles)
                .build();

        return userMapper.toUserDTO(userRepository.save(ins));

    }

    public LoggedUserDTO update(SignupDTO updateDTO, Long id) throws NoSuchElementException, UsernameAlreadyInUseException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!existingUser.getUsername().equals(updateDTO.getUsername()) && userRepository.existsByUsername(updateDTO.getUsername())) {
            throw new UsernameAlreadyInUseException("Error: Username is already taken!");
        }else {
            existingUser.setUsername(updateDTO.getUsername());
        }

        if (updateDTO.getPassword() != null && !updateDTO.getPassword().isEmpty()) {
            existingUser.setPassword(encoder.encode(updateDTO.getPassword()));
        } else {
           throw new RuntimeException("Password must not be empty");
        }

        Set<String> roleToBUpd = new HashSet<>();
        if (updateDTO.getUsertype() != null && !updateDTO.getUsertype().isEmpty()) {
            roleToBUpd.add(updateDTO.getUsertype());
            existingUser.setRoles(authService.createRoles(roleToBUpd));
        }

        userRepository.save(existingUser);

        LoggedUserDTO lgu = this.login(new LoginDTO(existingUser.getUsername(), updateDTO.getPassword()));
        jwtUtils.setRequestJwt(lgu.getJwt());

        return lgu;
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public UserDTO findByUsername(String username){
        return userMapper.toUserDTO(userRepository.findByUsername(username).get());
    }

    public List<UserDTO> findAllAdmin(){
        return userMapper.toUserDTOList(userRepository.findAllByRole(Role.ERole.ROLE_ADMIN));
    }

}
