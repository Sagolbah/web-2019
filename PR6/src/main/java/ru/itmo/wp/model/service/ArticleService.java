package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findByUser(long id) {
        return articleRepository.findByUser(id);
    }

    public void updateView(long id, boolean hide) {
        articleRepository.updateView(id, hide);
    }

}
