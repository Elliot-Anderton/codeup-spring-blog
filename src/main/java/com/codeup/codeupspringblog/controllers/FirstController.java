package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
    @GetMapping("/hello")
    @ResponseBody
    public String returnHelloWorld() {
        return "Hello World";
    }

    @GetMapping(value = "/html", produces = "text/html")
    @ResponseBody
    public String showHTML() {
        return "<h1>Hello World</h1>";
    }
}
