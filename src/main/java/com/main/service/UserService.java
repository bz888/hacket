package com.main.service;

import com.main.models.User;
import com.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Boolean isAdmin(User user) {
        return User.UserType.ADMIN.equals(user.getRole());
    }

    // TODO add Controller for this, should be only for internal use, perhaps it is not right to even have this
    public List<User> findAll(User user) {
        if (isAdmin(user)) {
            return userRepository.findAll();
        } else {
            throw new RuntimeException("Only admin access.");
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
