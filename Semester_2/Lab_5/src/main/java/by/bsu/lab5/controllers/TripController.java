package by.bsu.lab5.controllers;

import by.bsu.lab5.dao.TripDao;
import by.bsu.lab5.entity.Trip;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class TripController implements IController {
    @Override
    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        TripDao dao = new TripDao();
        List<Trip> trips = dao.getAll();
        ctx.setVariable("trips", trips);

        templateEngine.process("trip/list", ctx, writer);
    }
}
