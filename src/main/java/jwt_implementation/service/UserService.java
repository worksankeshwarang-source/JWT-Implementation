package jwt_implementation.service;

import jwt_implementation.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByUsername(String username);
}
