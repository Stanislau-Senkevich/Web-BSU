package by.bsu.lab5.controllers;
import by.bsu.lab5.dao.UserDao;
import by.bsu.lab5.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import java.io.Writer;
import java.util.Optional;

public class LoginController implements IController {
    private final UserDao userDao = new UserDao();
    @Override
    public void process(IWebExchange exchange, ITemplateEngine templateEngine, Writer writer,
                        final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        WebContext context = new WebContext(exchange, exchange.getLocale());
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            Optional<User> user = userDao.getByLogin(login);
            if (user.isPresent() && user.get().getPassword().equals(password)) {
                request.getSession().setAttribute("user", user.get());
                Cookie userCookie = new Cookie("userDetails", user.get().getLogin() + ":" + user.get().getAccessRole());
                userCookie.setMaxAge(60 * 60);
                userCookie.setPath("/");
                userCookie.setHttpOnly(true);
                userCookie.setSecure(request.isSecure());
                response.addCookie(userCookie);
                response.sendRedirect(request.getContextPath() + "/");
                return;
            } else {
                context.setVariable("error", "Invalid username or password.");
            }
        }
        templateEngine.process("login", context, writer);
    }
}