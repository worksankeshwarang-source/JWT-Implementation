package jwt_implementation.controller;

import jakarta.validation.Valid;
import jwt_implementation.dto.LoginRequest;
import jwt_implementation.dto.RegisterRequest;
import jwt_implementation.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){

        return authService.register(request);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login){

        return authService.login(login);

    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello! JWT Authentication Successful.");
    }

}
