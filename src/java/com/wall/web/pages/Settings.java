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

@WebServlet(urlPatterns = {"/settings"})
public class Settings extends AbstractServlet {

    @EJB
    private AppUserFacade facade;

    @Param
    private String name;

    @Param
    private String email;

    @Override
    protected void doGet() throws ServletException, IOException {
        forward("/settings");
    }

    private void validate() throws IllegalArgumentException {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Имя обязательно!");
        } else if (email == null || email.trim().length() == 0) {
            throw new IllegalArgumentException("Почта обязательна!");
        }
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        AppUser user = (AppUser) getSession().getAttribute("user");
        if (user == null) {
            redirect("/login");
        } else {
            try {
                validate();
                UserInfo info = user.getInfo();
                info.setEmail(email);
                info.setName(name);
                facade.edit(user);
                doGet();
            } catch (IllegalArgumentException e) {
                error(e.getMessage());
            }
        }
    }
}
