package com.redditsearcher.web.controller;

import com.redditsearcher.biz.manager.SearchManager;
import com.redditsearcher.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    SearchManager searchManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "q")
    public String home(ModelMap model, @RequestParam("q") String q) {
        List<Link> links = searchManager.search(q);
        model.addAttribute("links", links);
        return "home";
    }
}
