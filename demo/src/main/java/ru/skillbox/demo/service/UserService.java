package ru.skillbox.demo.service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.skillbox.demo.entity.User;
import ru.skillbox.demo.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) {
        if (user.getRegistrationDate() == null) {
            Date currentDate = new Date(new java.util.Date().getTime());
            user.setRegistrationDate(currentDate);
        }
        User savedUser = userRepository.save(user);
        return String.format("User %s added with id %s", savedUser.getName(), savedUser.getId());
    }

    @Transactional
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String updateUser(User user, long id) {
        if (userRepository.existsById(id)) {
            User savedUser = userRepository.save(user);
            return String.format("User %s was saved", savedUser.getName());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    public String deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return String.format("User id=%s was deleted", id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
