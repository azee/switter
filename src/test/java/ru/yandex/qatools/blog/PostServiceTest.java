package ru.yandex.qatools.blog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yandex.qatools.blog.beans.Comment;
import ru.yandex.qatools.blog.beans.Post;
import ru.yandex.qatools.blog.beans.User;
import ru.yandex.qatools.blog.services.PostService;
import ru.yandex.qatools.blog.services.UserService;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by azee on 29.09.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:context.xml")
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    private EntityManagerFactory emf;


    Map<String, User> users = new HashMap<>();

    @Before
    public void createUsers(){
        for (User user : userService.getAllUsers()){
            userService.removeEntity(user.getId());
        }
        users.put("Navalniy", userService.createEntity(new User("Navalniy")));
        users.put("Naklonniy", userService.createEntity(new User("Naklonniy")));
        users.put("Volodya", userService.createEntity(new User("Volodya")));
    }

    @After
    public void removePosts(){
        for (Post post : postService.getAllPosts()){
            postService.removeEntity(post.getId());
        }
    }

    @Test
    public void testPost(){
        Post post = new Post();
        post.setUser(users.get("Navalniy").getId());
        post.setText("Viva la resistance!");
        Post persistedPost = postService.createEntity(post);

        assertNotNull(persistedPost);
        assertThat(persistedPost.getText(), is(post.getText()));
        assertThat(persistedPost.getUser(), is(users.get("Navalniy").getId()));
    }

    @Test
    public void getPostsByUser(){
        Post post = new Post();
        post.setUser(users.get("Navalniy").getId());
        post.setText("I'm innocent!");
        postService.createEntity(post);

        post = new Post();
        post.setUser(users.get("Navalniy").getId());
        post.setText("Vote Naklonniy!");
        postService.createEntity(post);

        List<Post> posts = postService.getPosts(users.get("Navalniy").getId());

        assertNotNull(posts);
        assertThat(posts.size(), is(2));
        assertNotNull(posts.get(0));
        assertThat(posts.get(0).getText(), is("I'm innocent!"));
    }

    @Test
    public void addComment(){
        Post post = new Post();
        post.setUser(users.get("Volodya").getId());
        post.setText("I'm not a crab!");
        postService.createEntity(post);

        Comment comment = new Comment();
        comment.setText("LIE!");
        comment.setUser(users.get("Navalniy").getId());
        postService.addComment(comment, post.getId());

        comment = new Comment();
        comment.setText("No, I'm not a lier!");
        comment.setUser(users.get("Volodya").getId());
        postService.addComment(comment, post.getId());

        post = postService.getPostWithComments(post.getId());
        assertNotNull(post.getComments());
        assertThat(post.getComments().size(), is(2));

        assertNotNull(post.getComments().get(0));
        assertThat(post.getComments().get(0).getText(), is("LIE!"));
    }

}
