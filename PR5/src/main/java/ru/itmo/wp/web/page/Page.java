package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    protected final UserService userService = new UserService();
    private HttpServletRequest request;

    void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
        view.put("userCount", userService.userCount());
        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }

    }

    void after(HttpServletRequest request, Map<String, Object> view) {

    }

    void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    String getMessage() {
        return (String) request.getSession().getAttribute("message");
    }

    User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    void setUser(User user) {
        request.getSession().setAttribute("user", user);
    }

}
