package ru.yandex.qatools.blog.beans;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by azee on 29.09.14.
 */
@Entity
@Table(name="comment")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment extends Postable{

    public Post post;

    @ManyToOne(cascade=CascadeType.ALL, targetEntity = Post.class)
    @JoinColumn(name="post_id", referencedColumnName = "id", nullable = false, insertable = true, updatable = true)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Id
    @Override
    @Column(name = "id", unique=true, nullable=false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return super.getId();
    }

    @Override
    @Column(name = "text")
    public String getText() {
        return super.getText();
    }

    @Column(name = "date")
    @Override
    public long getPostDate() {
        return super.getPostDate();
    }

    @Column(name = "user")
    @Override
    public String getUser() {
        return super.getUser();
    }

    @Column(name = "title")
    @Override
    public String getTitle() {
        return super.getTitle();
    }
}
