package com.wall.logic;

import com.wall.models.AppUser;
import com.wall.models.Post;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PostFacade extends AbstractFacade<Post> {

    @PersistenceContext(unitName = "UsersPU")
    private EntityManager em;

    private String title;

    private String content;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }

    public void create(String title, String content, AppUser user) {
        this.title = title;
        this.content = content;
        Post post = new Post(title, content, user);
        create(post);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Post> selectAllPost() {
        return em.createNamedQuery("select-all-of-posts")
                .getResultList();
    }

    public Post findPostById(int id) {
        return (Post) em.createNamedQuery("select-post-by-id")
                .setParameter("id", id)
                .getSingleResult();
    }

}
