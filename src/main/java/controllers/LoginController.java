package controllers;

import infraSec.TokenService;
import jakarta.validation.Valid;
import models.DTOS.AuthenticationDTO;
import models.DTOS.LoginResponseDTO;
import models.DTOS.RegisterDTO;
import models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private infraSec.TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity LOGIN(@RequestBody AuthenticationDTO data){
        System.out.println(data);
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager
                .authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        System.out.println(token);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.userRole());

        this.userRepository.save(newUser);

        return  ResponseEntity.ok().build();
    }
}
