package com.wall.web.actions;

import com.wall.core.AbstractServlet;
import com.wall.core.binding.Param;
import com.wall.logic.CommentFacade;
import com.wall.models.Comment;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/delete-comment")
public class DeleteComment extends AbstractServlet {

    @EJB
    private CommentFacade commentFacade;

    @Param
    private int commentId;

    @Override
    protected void doPost() throws ServletException, IOException {
        Comment comment = commentFacade.findCommentById(commentId);
        commentFacade.remove(comment);
        redirect("/comments");
    }
}
