package com.wall.logic;

import com.wall.models.AppUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class AppUserFacade extends AbstractFacade<AppUser> {

    @PersistenceContext(unitName = "UsersPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppUserFacade() {
        super(AppUser.class);
    }

    public boolean loginAvailable(String login) {
        try {
            em.createNamedQuery("find-user-by-login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

    public AppUser findByLogin(String login) throws NoResultException {
        return (AppUser) em.createNamedQuery("find-user-by-login")
                .setParameter("login", login)
                .getSingleResult();
    }

    public AppUser findByEmail(String email) throws NoResultException {
        return (AppUser) em.createNamedQuery("find-user-by-email")
                .setParameter("email", email)
                .getSingleResult();
    }
}
