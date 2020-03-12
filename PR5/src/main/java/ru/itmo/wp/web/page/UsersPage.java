package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class UsersPage extends Page {
    private void action(Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    @Override
    void before(HttpServletRequest request, Map<String, Object> view){
        super.before(request, view);
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view){
        super.after(request, view);
    }
}
