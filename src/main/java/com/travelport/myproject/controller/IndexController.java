package com.travelport.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelport.myproject.service.ProcessService;

@Controller
public class IndexController {

    @Autowired
    private ProcessService mProcService;

    @RequestMapping("/")
    public String index() {
        // return "Greetings from Spring Boot! " +
        // mProcService.getSearchResponse();
        return "index.html";
    }

}