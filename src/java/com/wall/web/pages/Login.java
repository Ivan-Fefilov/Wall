package com.wall.web.pages;

import com.wall.core.AbstractServlet;
import com.wall.core.binding.Param;
import com.wall.logic.AppUserFacade;
import com.wall.models.AppUser;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/login"})
public class Login extends AbstractServlet {

    @EJB
    private AppUserFacade facade;

    @Param("login")
    private String name;

    @Param
    private String password;

    @Override
    protected void doGet() throws ServletException, IOException {
        forward("login");
    }

    private void validate() throws IllegalArgumentException {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name required!");
        }
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        try {
            validate();
            AppUser user = facade.findByLogin(name);
            if (user.checkPassword(password)) {
                save(user);
                redirect("/");
            } else {
                error("Неверный пароль!");
            }
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
        } catch (EJBException e) {
            error("Пользователь не найден!");
        }
    }
}
