package com.wall.web.actions;

import com.wall.core.AbstractServlet;
import com.wall.core.binding.Param;
import com.wall.logic.CommentFacade;
import com.wall.models.Comment;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/edit-comment")
public class EditComment extends AbstractServlet {

    @EJB
    private CommentFacade commentFacade;

    @Param
    private String content;

    @Param
    private int commentId;

    Comment comment;

    @Override
    protected void doGet() throws ServletException, IOException {
        comment = commentFacade.findCommentById(commentId);
        setAttribute("content", comment.getContent());
        forward("edit-comment");
    }

    @Override
    protected void doPost() throws ServletException, IOException {
        comment.setContent(content);
        comment.setEditTime(new Date());
        commentFacade.edit(comment);
        redirect("/comments");
    }
}
