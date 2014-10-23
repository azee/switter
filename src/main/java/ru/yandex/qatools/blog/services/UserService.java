package ru.yandex.qatools.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.qatools.blog.beans.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by azee on 29.09.14.
 */
@Service
public class UserService {

    @Autowired
    private javax.persistence.EntityManagerFactory emf;

    /**
     * Get all users in the system
     * @return
     */
    public List<User> getAllUsers(){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createNamedQuery("getAllUsers");
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    /**
     * Find a user by id
     * @param id
     * @return
     * @throws Exception
     */
    public User getUser(String id) {
        User user;
        EntityManager em = emf.createEntityManager();
        try{
            user = em.find(User.class, id);
        }
        finally {
            em.close();
        }
        return user;
    }

    /**
     * Count all users in the system
     * @return
     */
    public long countUsers(){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createNamedQuery("countUsers");
            return (Long)query.getSingleResult();
        }
        finally {
            em.close();
        }
    }

    /**
     * Create a user
     * @param user
     * @return
     * @throws Exception
     */
    public User createEntity(User user){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return user;
    }

    /**
     * Update a user
     * @param user
     * @return
     * @throws Exception
     */
    public User updateEntity(User user){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return user;
    }

    /**
     * Remove a user
     * @param id
     */
    public void removeEntity(String id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            User exampleEntity = em.find(User.class, id);
            em.remove(exampleEntity);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }



}
