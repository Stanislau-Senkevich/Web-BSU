package by.bsu.lab4.controllers;

import by.bsu.lab4.dao.TripDao;
import by.bsu.lab4.entity.Trip;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class TripController implements IController {
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        TripDao dao = new TripDao();
        List<Trip> trips = dao.getAll();
        ctx.setVariable("trips", trips);

        templateEngine.process("trip/list", ctx, writer);
    }
}
