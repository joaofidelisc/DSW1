package br.ufscar.dc.dsw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AA2Controler {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}