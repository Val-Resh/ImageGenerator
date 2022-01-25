package com.getinspired.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    /**
     * Primary controller that listens to HTTP GET requests on the main directory "/" on the tomcat server with predesignated port.
     * The method returns the name of the html file from default folder "templates". It will attempt to retrieve it whether it exists or not.
     * Thymeleaf is responsible for taking the string, attaching ".html", finding the CSS/JS dependencies in "static" folder.
     * @return index.html
     * @see "/src/main/resources/templates/index.html"
     *
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "index";
    }
}
