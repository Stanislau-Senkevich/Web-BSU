package by.bsu.lab6.controllers;

import by.bsu.lab6.entity.Car;
import by.bsu.lab6.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController implements IController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "car/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("car", new Car());
        return "car/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/car/list";
    }
}
