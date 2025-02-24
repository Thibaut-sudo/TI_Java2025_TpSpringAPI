package be.bstorm.demospringapi.bll.services.security;

import be.bstorm.demospringapi.dl.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void register(User user);
    User login(String email, String password);
}
