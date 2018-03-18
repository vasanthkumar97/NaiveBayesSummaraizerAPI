package com.summarizer.Controller;

import com.summarizer.Model.InputModel;
import com.summarizer.Model.Summary;
import com.summarizer.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path = "/reviewSummariser") // This means URL's start with /haystackApp (after Application path)

public class MainController {
    @Autowired
    Service service;

    @RequestMapping(method = RequestMethod.POST, path = "/summarise/{percentage}")
    @ResponseBody
    public Summary summarise(@RequestBody InputModel input, @PathVariable int percentage) {
        System.out.print("im here");
        Summary sum=service.getSummary(percentage, input);
        System.out.print(sum.getSummary());
        return sum;

    }
}

