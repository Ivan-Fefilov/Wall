package com.wall.web.actions;

import com.wall.core.AbstractServlet;
import com.wall.core.binding.Param;
import com.wall.logic.CommentFacade;
import com.wall.logic.PostFacade;
import com.wall.models.Post;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/delete")
public class DeletePost extends AbstractServlet {

    @EJB
    private PostFacade postFacade;
    
    @EJB
    private CommentFacade commentFacade;

    @Param
    private int postId;

    @Override
    protected void doPost() throws ServletException, IOException {
        Post post = postFacade.findPostById(postId);
        commentFacade.removeAllCommentsOfThePost(post);
        postFacade.remove(post);
        redirect("/");
    }

}
