package by.bsu.lab5.controllers;

import by.bsu.lab5.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class HomeController implements IController {

    @Override
    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        User user = (User) request.getSession(true).getAttribute("user");
        if (user != null) {
            ctx.setVariable("role", user.getAccessRole());
        } else {
            ctx.setVariable("role", "guest");
        }
        templateEngine.process("home", ctx, writer);
    }
}
