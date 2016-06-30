package com.wall.web.pages;

import com.wall.core.AbstractServlet;
import com.wall.core.binding.Param;
import com.wall.logic.CommentFacade;
import com.wall.logic.PostFacade;
import com.wall.models.AppUser;
import com.wall.models.Comment;
import com.wall.models.Post;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/comments"})
public class Comments extends AbstractServlet {

    @EJB
    private CommentFacade commentFacade;

    @EJB
    private PostFacade postFacade;

    @Param
    private String content;

    @Param
    int postId;

    private Post post;

    @Override
    protected void doGet() throws ServletException, IOException {
        post = postFacade.findPostById(postId);
        List<Comment> comments = commentFacade.selectAllCommentsForThePost(post);
        setAttribute("comments", comments);
        setAttribute("posts", post);
        forward("/comments");
    }

    public void validate() {
        if (content == null || content.trim().length() == 0) {
            throw new IllegalArgumentException("Контент обязателен!");
        }
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        try {
            validate();
            AppUser user = (AppUser) getSession().getAttribute("user");
            commentFacade.create(content, user, post);
            redirect("/comments");
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
        }
    }
}
