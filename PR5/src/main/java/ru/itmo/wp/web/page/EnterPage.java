package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.EventType;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @noinspection unused
 */
public class EnterPage extends Page {
    private final EventService eventService = new EventService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void enter(HttpServletRequest request) throws ValidationException {
        String loginOrEmail = request.getParameter("login_or_email");
        String password = request.getParameter("password");
        User user = userService.validateAndFindByLoginOrEmailAndPassword(loginOrEmail, password);
        setUser(user);
        setMessage("Hello, " + user.getLogin());
        Event event = new Event();
        event.setType(EventType.ENTER);
        event.setUserId(user.getId());
        eventService.saveEvent(event);
        throw new RedirectException("/index");
    }

    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }

}
