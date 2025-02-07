package by.bsu.lab4.controllers;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class HomeController implements IController {

    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext context = new WebContext(exchange, exchange.getLocale());
        templateEngine.process("home", context, writer);
    }
}
