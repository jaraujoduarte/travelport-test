package com.travelport.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travelport.myproject.model.LowFareSearchDto;
import com.travelport.myproject.service.ProcessService;

@Controller
public class IndexController {

    @Autowired
    private ProcessService procService;

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody LowFareSearchDto search() {
        return procService.processSearchResponse();
    }

}