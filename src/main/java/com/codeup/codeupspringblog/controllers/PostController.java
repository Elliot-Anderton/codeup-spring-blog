package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;

import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {
    private final PostRepository postDoa;
    private final UserRepository userDoa;
    private final EmailService emailService;

    public PostController(PostRepository postDoa, UserRepository userDoa, EmailService emailService) {
        this.postDoa = postDoa;
        this.userDoa = userDoa;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsHome(Model model) {
        model.addAttribute("posts", postDoa.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsHome(@PathVariable long id, Model model) {
        model.addAttribute("post",
                postDoa.getReferenceById(id));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String postsEdit(@PathVariable long id, Model model) {
        model.addAttribute("post",
                postDoa.getReferenceById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String postUpdate(@ModelAttribute Post post) {
        postDoa.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postsForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        System.out.println(post.getTitle());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDoa.save(post);

        emailService.sendPostEmail(post, "test", "Yo, Here's your ad");

        return "redirect:/posts";
    }
}
