package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    public int i = 0;
    public int thisRoll = 0;
    public int getRandom(){
        return (int)Math.floor(Math.random() * (6) + 1);
    }

    public void checkGuess(int num1, int num2, Model model) {
            if (num1 == num2) {
                model.addAttribute("message", "HOORAYYY!!! " +
                        num2 +
                        ", is correct");
            } else {
                model.addAttribute("message", "Sorry! " +
                                num2 +
                        ", is incorrect. Please try again");
            }
    }

    @GetMapping("/roll-dice")
    public String rollDice(Model model) {
        model.addAttribute("message", "Please roll the dice");
        return "roll-dice";
    }

    @GetMapping ("/roll-dice/{number}")
    public String rollDice(Model model, @PathVariable int number) {
        thisRoll = getRandom();
        model.addAttribute("dice", thisRoll);
        checkGuess(thisRoll, number, model);
        model.addAttribute("guesses", i++);
        return "roll-dice";
    }
}
