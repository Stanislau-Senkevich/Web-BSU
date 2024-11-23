package by.bsu.lab4.servlet;

import by.bsu.lab4.controllers.*;
import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMapping {

    private static final Map<String, IController> controllersByURL = new HashMap<>();

    static {
        controllersByURL.put("/", new HomeController());
        controllersByURL.put("/application/list", new ApplicationController());
        controllersByURL.put("/trip/list", new TripController());
        controllersByURL.put("/car/list", new CarController());
        controllersByURL.put("/user/list", new UserController());
    }

    public static IController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        return controllersByURL.get(path);
    }
}
