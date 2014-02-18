package com.redditsearcher.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home(ModelMap model, @RequestParam("q") String q) {
    public String home(ModelMap model) {
        model.addAttribute("message", "test");
        return "home";
    }
}
