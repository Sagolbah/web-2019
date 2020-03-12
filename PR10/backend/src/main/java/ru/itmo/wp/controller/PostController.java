package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.util.BindingResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController extends ApiController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @PostMapping("posts")
    public List<Post> createPost(@RequestBody @Valid PostCredentials postCredentials, BindingResult bindingResult, User user) {
        if (user == null) {
            throw new ValidationException("You're not logged in. Refresh the page and login again.");
        }
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultUtils.getErrorMessage(bindingResult));
        }
        postService.save(postCredentials, user);
        return postService.findAll();
    }

}
