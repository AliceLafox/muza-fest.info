package net.lafox.muza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "APP_USER")
@SuppressWarnings("unused")
public class User {
    @Id
    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name="APP_USER_ROLE",
        joinColumns=@JoinColumn(name="USERNAME"),
        inverseJoinColumns=@JoinColumn(name="ROLENAME"))
    private Collection<Role> roles;

    @Column(length = 40)
    private String token;

    //////////////////////////////////////////////////////////////

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}