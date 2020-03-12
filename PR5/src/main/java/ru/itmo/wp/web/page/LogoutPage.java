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
public class LogoutPage extends Page {
    private final EventService eventService = new EventService();

    private void action(HttpServletRequest request) {
        long id = getUser().getId();
        request.getSession().removeAttribute("user");
        Event event = new Event();
        event.setType(EventType.LOGOUT);
        event.setUserId(id);
        eventService.saveEvent(event);
        setMessage("Good bye. Hope to see you soon!");
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
