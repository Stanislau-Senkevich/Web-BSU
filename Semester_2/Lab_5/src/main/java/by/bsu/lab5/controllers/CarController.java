package by.bsu.lab5.controllers;

import by.bsu.lab5.dao.CarDao;
import by.bsu.lab5.dao.UserDao;
import by.bsu.lab5.entity.Car;
import by.bsu.lab5.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class CarController implements IController {
    @Override
    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String path = request.getRequestURI();
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        User user = (User) request.getSession(true).getAttribute("user");

        if ("/car/add".equals(path) && "POST".equalsIgnoreCase(request.getMethod())) {
            addCar(request, response);
        } else if ("/car/add".equals(path) && "GET".equalsIgnoreCase(request.getMethod())) {
            addCarForm(webExchange, templateEngine, writer);
        } else {
            if (user != null) {
                ctx.setVariable("role", user.getAccessRole());
            } else {
                ctx.setVariable("role", "guest");
            }

            CarDao dao = new CarDao();
            List<Car> cars = dao.getAll();
            ctx.setVariable("cars", cars);
            templateEngine.process("car/list", ctx, writer);
        }
    }

    private void addCar(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserDao userDao = new UserDao();
            CarDao carDao = new CarDao();
            String driver_id = request.getParameter("driver_id");
            String fix_state = request.getParameter("fix_state");
            String state_number = request.getParameter("state_number");
            User driver = userDao.getById(Integer.parseInt(driver_id)).get();
            Car car = new Car(0, driver, fix_state, state_number);
            carDao.insert(car);
            response.sendRedirect(request.getContextPath() + "/car/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCarForm(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer) {
        try {
            WebContext context = new WebContext(exchange, exchange.getLocale());
            templateEngine.process("/car/add", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carList(WebContext context) {

    }
}
