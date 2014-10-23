package ru.yandex.qatools.blog.resource;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.blog.beans.Comment;
import ru.yandex.qatools.blog.beans.Post;
import ru.yandex.qatools.blog.services.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by azee on 02.10.14.
 */
@Component
@Path("post")
public class PostResource {

    @Autowired
    PostService postService;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getPost(){
        return new Viewable("index.ftl");
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getPost(@PathParam("id") int id){
        return new Viewable("post.ftl", postService.getPostWithComments(id));
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getAllPosts(){
        return new Viewable("list.ftl", postService.getAllPosts());
    }

    @GET
    @Path("/new")
    @Produces(MediaType.TEXT_HTML)
    public Viewable newPost(){
        return new Viewable("new.ftl", new Post());
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getPostsByUser(@PathParam("id") String userId){
        return new Viewable("list.ftl", postService.getPosts(userId));
    }

    @GET
    @Path("/my")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getPostsByUser(@Context HttpServletRequest hsr){
        return new Viewable("list.ftl", postService.getPosts(hsr.getRemoteUser()));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Viewable getCreatePost(MultivaluedMap<String,String> data, @Context HttpServletRequest hsr){
        Post post = new Post(data);
        post.setUser(hsr.getRemoteUser());
        postService.createEntity(post);
        return new Viewable("post.ftl", post);
    }

    @POST
    @Path("/{id}/comment")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Viewable getCreatePost(MultivaluedMap<String,String> data,
                                  @PathParam("id") int postId,
                                  @Context HttpServletRequest hsr){
        Comment comment = new Comment();
        comment.setTitle(data.get("title").get(0));
        comment.setText(data.get("text").get(0));
        comment.setUser(hsr.getRemoteUser());
        postService.addComment(comment, postId);
        return new Viewable("post.ftl", postService.getPostWithComments(postId));
    }
}
