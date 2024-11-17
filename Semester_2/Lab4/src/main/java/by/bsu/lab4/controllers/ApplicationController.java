package by.bsu.controllers;

import by.bsu.lab4.dao.ApplicationDao;
import by.bsu.lab4.entity.Application;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class ApplicationController implements IController {

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ApplicationDao dao = new ApplicationDao();
        List<Application> applications = dao.getAll();
        ctx.setVariable("applications", applications);

        templateEngine.process("application/list", ctx, writer);
    }

}
