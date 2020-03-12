package ru.itmo.wp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;

import javax.persistence.Lob;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final CommentService commentService;

    public PostPage(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping({"/post/", "/post"})
    @Guest
    public String postEmpty() {
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    @Guest
    public String post(Model model, @PathVariable("id") String value) {
        try {
            Post viewedPost = postService.findById(Long.parseLong(value));
            model.addAttribute("viewedPost", viewedPost);
            model.addAttribute("commentForm", new CommentCredentials());
        } catch (NumberFormatException ignored) {
            // No operations
        }
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String postComment(Model model, @Valid @ModelAttribute("commentForm") CommentCredentials commentForm,
                              BindingResult bindingResult, HttpSession httpSession, @PathVariable("id") String value) {
        try {
            Post viewedPost = postService.findById(Long.parseLong(value));
            model.addAttribute("viewedPost", viewedPost);
            if (bindingResult.hasErrors()) {
                return "PostPage";
            }
            commentService.addComment(commentForm, viewedPost, getUser(httpSession));
            putMessage(httpSession, "Comment posted successfully.");
        } catch (NumberFormatException ignored) {
            // No operations
        }
        return "PostPage";
    }
}
