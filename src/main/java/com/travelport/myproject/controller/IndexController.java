package com.travelport.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelport.myproject.service.ProcessService;

@RestController
public class IndexController {

    @Autowired
    private ProcessService mProcService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! " + mProcService.getSearchResponse();
    }

}