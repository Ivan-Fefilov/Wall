package com.wall.web.pages;

import com.wall.core.AbstractServlet;
import com.wall.core.binding.Param;
import com.wall.logic.PostFacade;
import com.wall.models.AppUser;
import com.wall.models.Post;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/index.html"})
public class Index extends AbstractServlet {

    @EJB
    private PostFacade facade;

    @Param
    private String title;

    @Param
    private String content;

    public void validate() {
        if (title == null || title.trim().length() == 0) {
            throw new IllegalArgumentException("Название обязательно!");
        } else if (content == null || content.trim().length() == 0) {
            throw new IllegalArgumentException("Контент обязателен!");
        }
    }

    @Override
    protected void doGet() throws ServletException, IOException {
        List<Post> posts = facade.selectAllPost();
        Collections.reverse(posts);
        setAttribute("posts", posts);
        forward("index");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        try {
            validate();
            AppUser user = (AppUser) getSession().getAttribute("user");
            facade.create(title, content, user);
            redirect("/");
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
        }
    }
}
