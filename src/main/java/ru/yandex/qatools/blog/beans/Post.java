package ru.yandex.qatools.blog.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by azee on 29.09.14.
 */

@NamedQueries({
        @NamedQuery(
                name = "getAllPosts",
                query = "SELECT e FROM Post e"
        ),
        @NamedQuery(
                name = "getUsersPosts",
                query = "SELECT e FROM Post e WHERE e.user = :userId"
        )
})
@Entity
@Table(name="post")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Post extends Postable {

    public Post() {
        super();
    }

    public Post(String userId, String title, String text, long postDate) {
        super(userId, title, text, postDate);
    }

    public Post(MultivaluedMap<String,String> data){
        List<String> paramList = data.get("title");
        if (paramList != null || paramList.size() > 0){
            title = paramList.get(0);
        } else {
            title = "";
        }

        paramList = data.get("text");
        if (paramList != null || paramList.size() > 0){
            text = paramList.get(0);
        } else {
            text = "";
        }

        postDate = new Date().getTime();
    }

    List<Comment> comments;

    @OneToMany(targetEntity = Comment.class, mappedBy = "post", cascade = CascadeType.ALL)
    public List<Comment> getComments() {
        if (comments == null){
            comments = new ArrayList<Comment>();
        }
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    @Column(name = "text")
    public String getText() {
        return super.getText();
    }

    @Override
    @Column(name = "date")
    public long getPostDate() {
        return super.getPostDate();
    }

    @Id
    @Override
    @Column(name = "id", unique=true, nullable=false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return super.getId();
    }

    @Override
    @Column(name = "user")
    public String getUser() {
        return super.getUser();
    }

    @Override
    @Column(name = "title")
    public String getTitle() {
        return super.getTitle();
    }
}
