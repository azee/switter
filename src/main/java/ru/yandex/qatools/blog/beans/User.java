package ru.yandex.qatools.blog.beans;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by azee on 29.09.14.
 */
@NamedQueries({
        @NamedQuery(
                name = "getAllUsers",
                query = "SELECT e FROM User e"
        ),
        @NamedQuery(
                name = "getUser",
                query = "SELECT e FROM User e WHERE e.id = :userId"
        ),
        @NamedQuery(
                name = "countUsers",
                query = "SELECT COUNT(e) FROM User e"
        )
})
@Entity
@Table(name="users")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    public User() {}

    public User(String name) {
        this.name = name;
        this.id = name;
    }

    public User(String name, String password) {
        this.id = name;
        this.name = name;
        this.password = password;
    }

    @Id
    @Column(name = "id", unique=true, nullable=false)
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "password")
    String password;

    @Column(name = "token")
    String token;

    @Column(name = "expirationDate")
    long tokenExpiresAt;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTokenExpiresAt() {
        return tokenExpiresAt;
    }

    public void setTokenExpiresAt(long tokenExpiresAt) {
        this.tokenExpiresAt = tokenExpiresAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
