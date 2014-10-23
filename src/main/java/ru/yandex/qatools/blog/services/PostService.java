package ru.yandex.qatools.blog.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.qatools.blog.beans.Comment;
import ru.yandex.qatools.blog.beans.Post;
import ru.yandex.qatools.blog.exceptions.PostNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azee on 29.09.14.
 */
@Service
public class PostService {

    @Autowired
    private EntityManagerFactory emf;

    /**
     * Get all posts in the system
     * @return
     */
    public List<Post> getPosts(String userId){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createNamedQuery("getUsersPosts", Post.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    /**
     * Return all posts
     * @return
     */
    public List<Post> getAllPosts(){
        EntityManager em = emf.createEntityManager();
        List<Post> result;
        try{
            Query query = em.createNamedQuery("getAllPosts");
            result = query.getResultList();
            return (result == null) ? new ArrayList<Post>() : result;
        }
        finally {
            em.close();
        }
    }

    /**
     * Find a post by id
     * @param id
     * @return
     * @throws Exception
     */
    public Post getPost(int id) {
        Post post;
        EntityManager em = emf.createEntityManager();
        try{
            post = em.find(Post.class, id);
        }
        finally {
            em.close();
        }
        return post;
    }

    /**
     * Find a post by id
     * @param id
     * @return
     * @throws Exception
     */
    public Post getPostWithComments(int id) {
        Post post;
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            post = em.find(Post.class, id);
            Hibernate.initialize(post.getComments());
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return post;
    }

    /**
     * Create a post
     * @param post
     * @return
     * @throws Exception
     */
    public Post createEntity(Post post) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(post);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return post;
    }


    /**
     * Update a post
     * @param post
     * @return
     * @throws Exception
     */
    public Post updateEntity(Post post) throws Exception {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(post);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return post;
    }

    /**
     * Remove a post
     * @param id
     */
    public void removeEntity(int id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Post exampleEntity = em.find(Post.class, id);
            em.remove(exampleEntity);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }

    /**
     * Add a comment to a post
     * @param comment
     * @param postId
     * @return
     */
    public Comment addComment(Comment comment, int postId){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Post post = em.find(Post.class, postId);
            if (post == null){
                throw new PostNotFoundException("Post not found");
            }
            comment.setPost(post);
            post.getComments().add(comment);


            em.persist(comment);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return comment;
    }
}
