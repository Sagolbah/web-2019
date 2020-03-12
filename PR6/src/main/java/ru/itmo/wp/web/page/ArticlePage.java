package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        checkUser(request);
    }

    private void submitArticle(HttpServletRequest request, Map<String, Object> view) {
        checkUser(request);
        String title = request.getParameter("title");
        String text = request.getParameter("article_text");
        User currentUser = (User) request.getSession().getAttribute("user");
        Article article = new Article(currentUser.getId(), title, text);
        articleService.save(article);
        request.getSession().setAttribute("message", "Article submitted successfully.");
        throw new RedirectException("/index");
    }

    private void checkUser(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "You need to login to post the article.");
            throw new RedirectException("/index");
        }
    }

}
