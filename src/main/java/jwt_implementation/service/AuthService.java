package jwt_implementation.service;

import jwt_implementation.dto.LoginRequest;
import jwt_implementation.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> register(RegisterRequest request);

    ResponseEntity<?> login(LoginRequest login);

}
