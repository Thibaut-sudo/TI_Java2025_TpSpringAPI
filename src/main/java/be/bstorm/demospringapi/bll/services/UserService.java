package be.bstorm.demospringapi.bll.services;

import be.bstorm.demospringapi.bll.models.forms.user.UserFilter;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

//    List<User> getUsers(UserFilter filter);
    Page<User> getUsers(List<SearchParam<User>> searchParams, Pageable pageable);
}
