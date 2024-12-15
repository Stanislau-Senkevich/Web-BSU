package by.bsu.lab6.controllers;

import by.bsu.lab6.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trip")
public class TripController implements IController {
    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("trips", tripRepository.findAll());
        return "trip/list";
    }
}
