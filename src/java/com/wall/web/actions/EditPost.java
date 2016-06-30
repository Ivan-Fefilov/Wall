package com.wall.web.actions;

import com.wall.core.AbstractServlet;
import com.wall.logic.PostFacade;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import com.wall.core.binding.Param;
import com.wall.models.Post;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;

@WebServlet(urlPatterns = "/edit-post")
public class EditPost extends AbstractServlet {

    @EJB
    private PostFacade postFacade;

    @Param
    private String title;

    @Param
    private String content;

    @Param
    private int postId;

    private Post post;

    @Override
    protected void doGet() throws ServletException, IOException {
        post = postFacade.findPostById(postId);
        setAttribute("title", post.getTitle());
        setAttribute("content", post.getContent());
        forward("edit-post");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        post.setTitle(title);
        post.setContent(content);
        post.setEditTime(new Date());
        postFacade.edit(post);
        redirect("/");
    }

}
