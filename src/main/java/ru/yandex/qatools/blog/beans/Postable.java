package ru.yandex.qatools.blog.beans;


/**
 * Created by azee on 29.09.14.
 */
public class Postable {
    String user;
    String title;
    String text;
    long postDate;
    Integer id;

    public Postable(String user, String title, String text, long postDate) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.postDate = postDate;
    }

    public Postable() {}

    public String getUser() {
        return user;
    }

    public void setUser(String userId) {
        this.user = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getPostDate() {
        return postDate;
    }

    public void setPostDate(long postDate) {
        this.postDate = postDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
