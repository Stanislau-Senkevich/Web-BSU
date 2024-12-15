package by.bsu.lab6.controllers;

import by.bsu.lab6.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/application")
public class ApplicationController implements IController {
    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("applications", applicationRepository.findAll());
        return "application/list";
    }
}
