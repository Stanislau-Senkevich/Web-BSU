package by.bsu.lab5.controllers;

import by.bsu.lab5.dao.UserDao;
import by.bsu.lab5.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class UserController implements IController {
    @Override
    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        UserDao dao = new UserDao();
        List<User> users = dao.getAll();
        ctx.setVariable("users", users);

        templateEngine.process("user/list", ctx, writer);
    }
}
