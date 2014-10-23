package ru.yandex.qatools.blog.resource;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.qatools.blog.services.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by azee on 03.10.14.
 */
@Path("/")
public class IndexResource {

    @Autowired
    UserService userService;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getPost(){
        return new Viewable("index.ftl", userService.getAllUsers());
    }
}
