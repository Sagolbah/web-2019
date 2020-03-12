package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ManagePage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        checkUser(request);
        User user = (User) request.getSession().getAttribute("user");
        view.put("articles", articleService.findByUser(user.getId()));
    }

    private void checkUser(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "You need to login to manage your articles.");
            throw new RedirectException("/index");
        }
    }

    private void show(HttpServletRequest request, Map<String, Object> view) {
        checkUser(request);
        System.out.println(request.getParameter("articleId"));
        long articleId = Long.parseLong(request.getParameter("articleId"));
        articleService.updateView(articleId, false);
        // throw new RedirectException("/index");
    }

    private void hide(HttpServletRequest request, Map<String, Object> view) {
        checkUser(request);
        System.out.println(request.getParameter("articleId"));
        long articleId = Long.parseLong(request.getParameter("articleId"));
        articleService.updateView(articleId, true);
        // throw new RedirectException("/index");
    }

}
