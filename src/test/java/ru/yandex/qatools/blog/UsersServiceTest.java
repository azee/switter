package ru.yandex.qatools.blog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yandex.qatools.blog.beans.User;
import ru.yandex.qatools.blog.services.UserService;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by azee on 29.09.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:context.xml")
public class UsersServiceTest {

    @Autowired
    UserService userService;

    @Before
    public void removeAllUsers(){
        for (User user : userService.getAllUsers()){
            userService.removeEntity(user.getId());
        }
    }

    @Test
    public void testCreation(){
        User user = new User("Guf");
        user.setToken("guf_token");
        user.setTokenExpiresAt(new Date().getTime() + 604800000L);
        User persistedUser = userService.createEntity(user);

        assertNotNull(persistedUser);
        assertThat(persistedUser.getName(), is(user.getName()));
        assertThat(persistedUser.getToken(), is(user.getToken()));
        assertTrue(persistedUser.getTokenExpiresAt() > new Date().getTime());
    }

    @Test
    public void testRemoving(){
        User pushkin = new User("Pushkin");
        pushkin = userService.createEntity(pushkin);

        User dantes = new User("Dantes");
        dantes = userService.createEntity(dantes);

        userService.removeEntity(pushkin.getId());

        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertThat(users.size(), is(1));
        assertNotNull(users.get(0));
        assertThat(users.get(0).getName(), is(dantes.getName()));
    }

    @Test
    public void testFetching(){
        userService.createEntity(new User("Don Huan"));
        userService.createEntity(new User("Carlito"));

        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertThat(users.size(), is(2));
    }

    @Test
    public void testCounting(){
        userService.createEntity(new User("User1"));
        userService.createEntity(new User("User2"));
        userService.createEntity(new User("User3"));
        assertThat(userService.countUsers(), is(3L));
    }
}
