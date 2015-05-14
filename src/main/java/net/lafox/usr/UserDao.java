package net.lafox.usr;


import net.lafox.muza.entity.User;

import java.util.Collection;

public interface UserDao {
    User findUserByUsername(String username);
    Collection<User> list(String roleName);
    User findUserByToken(String token);
}