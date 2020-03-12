package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.UserCredentialsEnter;
import ru.itmo.wp.form.UserCredentialsRegister;
import ru.itmo.wp.form.validator.UserCredentialsRegisterValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UserController extends ApiController {
    private final UserService userService;
    private final UserCredentialsRegisterValidator userCredentialsRegisterValidator;
    private final JwtService jwtService;


    @InitBinder("userCredentialsRegister")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegisterValidator);
    }


    public UserController(UserService userService, UserCredentialsRegisterValidator validator, JwtService jwtService) {
        this.userService = userService;
        this.userCredentialsRegisterValidator = validator;
        this.jwtService = jwtService;
    }

    @GetMapping("users/authorized")
    public User findAuthorized(User user) {
        return user;
    }

    @GetMapping("users")
    public List<User> findUsers() {
        return userService.findAll();
    }

    @PostMapping("users")
    public String register(@RequestBody @Valid UserCredentialsRegister userCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        User user = userService.register(userCredentials);
        return jwtService.create(user);
    }
}
