package com.cardapios.cardapios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerHome {

    @GetMapping("/")
    public String home(){
        return "Home-Page";
    }
}
