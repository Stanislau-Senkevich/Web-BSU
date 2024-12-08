package by.bsu.lab5.controllers;

import by.bsu.lab5.dao.ApplicationDao;
import by.bsu.lab5.entity.Application;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class ApplicationController implements IController {

    @Override
    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ApplicationDao dao = new ApplicationDao();
        List<Application> applications = dao.getAll();
        ctx.setVariable("applications", applications);

        templateEngine.process("application/list", ctx, writer);
    }

}
