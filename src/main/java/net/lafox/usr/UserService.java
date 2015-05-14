package net.lafox.usr;

import net.lafox.muza.entity.User;

import java.util.Collection;

public interface UserService {
    User getCurrentUser();
    Collection<User> list(String roleName);
    User findUserByUsername(String username);
    User findUserByToken(String token);

}
