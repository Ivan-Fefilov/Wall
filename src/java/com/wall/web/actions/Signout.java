package com.wall.web.actions;

import com.wall.core.AbstractServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/signout")
public class Signout extends AbstractServlet {

    @Override
    protected void doGet() throws ServletException, IOException {
        endSession();
        redirect("/");
    }
}
