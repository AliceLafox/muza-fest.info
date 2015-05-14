package net.lafox.muza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="APP_ROLE")
@SuppressWarnings("unused")
public class Role{
    @Id
    @Column(name="ROLENAME", nullable=false)
    private String roleName;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name="APP_USER_ROLE",
        joinColumns=@JoinColumn(name="ROLENAME"),
        inverseJoinColumns=@JoinColumn(name="USERNAME"))
    private Collection<User> users;

    /////////////////////////////////////////////////////////

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Collection<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

}
