package net.lafox.usr;

import java.util.ArrayList;
import java.util.Collection;

import net.lafox.muza.entity.Role;
import net.lafox.muza.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Qualifier("userDaoImpl")
    @Autowired
    private UserDao userDao;

    @Transactional
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
        logger.info("Spring security loading user details for user: " + username);
        User user = userDao.findUserByUsername(username);
        System.out.println("user = " + user);
        Collection<Role> roles = user.getRoles();
        System.out.println("roles = " + roles);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
    
    public User getCurrentUser() {
        return userDao.findUserByUsername(
                ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().
                        getAuthentication().getPrincipal()).getUsername());
    }

    @Override
    public Collection<User> list(String roleName) {
        return userDao.list(roleName);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public User findUserByToken(String token) {
        return userDao.findUserByToken(token);
    }

}
