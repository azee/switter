package ru.yandex.qatools.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import java.util.Arrays;

/**
 * Created by azee on 03.10.14.
 */
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ru.yandex.qatools.blog.beans.User user = userService.getUser(s);
        if (user == null){
            throw new UsernameNotFoundException(String.format("User %s doesn't exist", s));
        }

        return new User(user.getName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

    }
}
