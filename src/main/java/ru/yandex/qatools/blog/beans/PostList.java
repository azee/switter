package ru.yandex.qatools.blog.beans;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 02.10.14.
 */
public class PostList {
    private List<Post> posts;

    public PostList(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        if (posts == null){
            posts = new LinkedList<>();
        }
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
