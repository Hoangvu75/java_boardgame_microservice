package com.boardgame.contractservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    
    @GetMapping("/")
    public String service() {
        return "Contract service";
    }
}
