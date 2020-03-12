package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.FormattedMessage;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalksService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TalksPage extends Page {
    private final TalksService talksService = new TalksService();
    private List<User> availableUsers;

    private void action(HttpServletRequest request, Map<String, Object> view) {
    }

    private void submitMessage(HttpServletRequest request) throws ValidationException {
        String target = request.getParameter("target_user");
        String message = request.getParameter("talk");
        boolean targetExists = false;
        for (User u : availableUsers) {
            if (u.getLogin().equals(target)) {
                targetExists = true;
                break;
            }
        }
        if (!targetExists) {
            throw new ValidationException("The target user does not exist.");
        }
        Talk talk = new Talk(message, getUser().getId(), Long.parseLong(target));
        talksService.save(talk);
        throw new RedirectException("/talks");  // auto refresh
    }


    @Override
    void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            setMessage("You need to login to see the messages.");
            throw new RedirectException("/index");
        }
        availableUsers = userService.findAll();
        view.put("users", availableUsers);
        List<Talk> userTalks = talksService.findAllWithUser(getUser().getId());
        List<FormattedMessage> formattedTalks = new ArrayList<>();
        for (Talk talk : userTalks) {
            formattedTalks.add(new FormattedMessage(userService, talk));
        }
        view.put("messages", formattedTalks);
    }

    @Override
    void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
