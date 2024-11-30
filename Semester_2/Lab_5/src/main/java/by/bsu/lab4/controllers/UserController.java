package by.bsu.lab4.controllers;

import by.bsu.lab4.dao.UserDao;
import by.bsu.lab4.entity.User;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class UserController implements IController {
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        UserDao dao = new UserDao();
        List<User> users = dao.getAll();
        ctx.setVariable("users", users);

        templateEngine.process("user/list", ctx, writer);
    }
}
