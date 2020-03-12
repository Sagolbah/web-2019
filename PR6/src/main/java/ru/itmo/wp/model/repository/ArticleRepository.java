package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    Article find(long id);

    List<Article> findAll();

    List<Article> findByUser(long id);

    void save(Article article);

    void updateView(long id, boolean hide);
}
