package ru.yandex.qatools.blog.resource;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.blog.beans.User;
import ru.yandex.qatools.blog.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


/**
 * Created by azee on 02.10.14.
 */
@Component
@Path("user")
public class UserResource {

    @Autowired
    UserService userService;

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public Viewable login(){
        return new Viewable("login.ftl");
    }

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public Viewable create(){
        return new Viewable("create.ftl");
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Viewable createUser(MultivaluedMap<String,String> data, @Context HttpServletRequest hsr){
        User user = new User(data.get("name").get(0), data.get("password").get(0));
        userService.createEntity(user);
        return new Viewable("login.ftl");
    }
}
