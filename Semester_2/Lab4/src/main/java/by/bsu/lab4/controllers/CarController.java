package by.bsu.controllers;

import by.bsu.lab4.dao.CarDao;
import by.bsu.lab4.entity.Car;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class CarController implements IController {
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        CarDao dao = new CarDao();
        List<Car> cars = dao.getAll();
        ctx.setVariable("cars", cars);

        templateEngine.process("car/list", ctx, writer);
    }
}
