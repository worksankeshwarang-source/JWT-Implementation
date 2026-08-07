package jwt_implementation.implementationService;

import jwt_implementation.dto.LoginRequest;
import jwt_implementation.dto.RegisterRequest;
import jwt_implementation.entity.User;
import jwt_implementation.service.AuthService;
import jwt_implementation.service.JwtService;
import jwt_implementation.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, JwtService jwtService,
                           PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

    }
    /**
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<?> register(RegisterRequest request) {

        Optional<User>  existingUser = userService.findByUsername(request.getUsername());

        if(existingUser.isPresent()){
            return ResponseEntity.badRequest().body("User Already Exist...");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserRole(request.getUserRole());

        userService.saveUser(user);

        return ResponseEntity.ok("User Registered Successfully...");
    }

    /**
     * @param loginRequest
     * @return
     */
    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        String token = jwtService.generateToken(loginRequest.getUsername());
        return ResponseEntity.ok(token);
    }
}
