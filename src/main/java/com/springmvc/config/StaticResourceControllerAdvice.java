package com.springmvc.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class StaticResourceControllerAdvice {

    private final String staticResourceUrl = "";

    @ModelAttribute
    public void addStaticResourceUrl(Model model) {
        model.addAttribute("BASE_URL", staticResourceUrl);
    }
}