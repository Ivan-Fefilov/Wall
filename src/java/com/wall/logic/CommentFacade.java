package com.wall.logic;

import com.wall.models.AppUser;
import com.wall.models.Comment;
import com.wall.models.Post;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CommentFacade extends AbstractFacade<Comment> {

    @PersistenceContext(unitName = "UsersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }

    public void create(String content, AppUser user, Post post) {
        Comment comment = new Comment(content, user, post);
        create(comment);
    }

    public void removeAllCommentsOfThePost(Post post) {
        List<Comment> comments = selectAllCommentsForThePost(post);
        for (Comment comment : comments) {
            remove(comment);
        }
    }

    public List<Comment> selectAllCommentsForThePost(Post post) {
        return em.createNamedQuery("select-all-comments-to-post")
                .setParameter("post", post)
                .getResultList();
    }

    public Comment findCommentById(int id) {
        return (Comment) em.createNamedQuery("select-comment-by-id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
