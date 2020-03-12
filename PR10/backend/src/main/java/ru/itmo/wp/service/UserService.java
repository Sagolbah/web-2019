package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.UserCredentialsEnter;
import ru.itmo.wp.form.UserCredentialsRegister;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User register(UserCredentialsRegister userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        user.setName(userCredentials.getName());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userCredentials.getLogin(), userCredentials.getPassword());
        return user;
    }
}
