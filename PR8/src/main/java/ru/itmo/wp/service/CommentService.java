package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllByPostId(long id) {
        return commentRepository.findAllByPostId(id);
    }

    public void addComment(CommentCredentials commentCredentials, Post sourcePost, User user) {
        Comment comment = new Comment();
        comment.setText(commentCredentials.getText());
        comment.setPost(sourcePost);
        comment.setUser(user);
        commentRepository.save(comment);
    }
}
