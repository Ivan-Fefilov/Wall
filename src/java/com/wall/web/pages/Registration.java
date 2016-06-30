package com.wall.web.pages;

import com.wall.core.AbstractServlet;
import com.wall.core.binding.Param;
import com.wall.logic.AppUserFacade;
import com.wall.models.AppUser;
import com.wall.models.UserInfo;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/registration"})
public class Registration extends AbstractServlet {

    @EJB
    private AppUserFacade facade;

    @Param
    private String login;

    @Param
    private String name;

    @Param
    private String email;

    @Param
    private String password;

    @Param("confirm")
    private String confirmation;

    @Override
    protected void doGet() throws ServletException, IOException {
        forward("registration");
    }

    private void validate() throws IllegalArgumentException {
        if (login == null || login.trim().length() == 0) {
            throw new IllegalArgumentException("Логин обязательно!");
        } else if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Имя обязательно!");
        } else if (email == null || email.trim().length() == 0) {
            throw new IllegalArgumentException("Почта обязательна!");
        } else if (password == null || password.trim().length() == 0) {
            throw new IllegalArgumentException("Пароль обязательно!");
        } else if (!password.equals(confirmation)) {
            throw new IllegalArgumentException("Пароли не совпадают!");
        }
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        try {
            validate();
            UserInfo info = new UserInfo(name, email);
            AppUser user = new AppUser(login, password, info);
            facade.create(user);
            redirect("/login");
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
        }
    }
}
