package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class MathController {
    @GetMapping("/{method}/{num1}/{middle}/{num2}")
    @ResponseBody
    public int math(@PathVariable int num1, @PathVariable String method, @PathVariable int num2) {
        int answer = 0;

        if (Objects.equals(method, "add")) {
            answer = num1 + num2;
        } else if (Objects.equals(method, "multiply")) {
            answer = num1 * num2;
        } else if (Objects.equals(method, "subtract")) {
            answer = num2 - num1;
        } else if (Objects.equals(method, "divide")) {
            answer = num1 / num2;
        }
        return answer;
    }
}
