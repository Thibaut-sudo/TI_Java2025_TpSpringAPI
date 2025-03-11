package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.bll.exceptions.Panier.PanierNotFoundException;
import be.bstorm.demospringapi.bll.exceptions.user.UserNotFoundException;
import be.bstorm.demospringapi.bll.services.UserService;
import be.bstorm.demospringapi.dal.repositories.UserRepository;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import be.bstorm.demospringapi.il.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getUsers(List<SearchParam<User>> searchParams, Pageable pageable) {
        Page<User> users;

        if (searchParams.isEmpty()) {
            users = userRepository.findAll(pageable);
        } else {
            users = userRepository.findAll(
                    Specification.allOf(
                            searchParams.stream()
                                    .map(SearchSpecification::search)
                                    .toList()
                    ),
                    pageable
            );
        }
        if (users.isEmpty()) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "utulisateur non trouvé");
        }
        return users;
    }
}

//    @Override
//    public List<User> getUsers(UserFilter filter) {
//        Specification<User> spec = Specification.where(null);
//
//        if(filter.firstName() != null && !filter.firstName().isEmpty()){
//            spec = spec.and(UserSpecification.getByFirstName(filter.firstName()));
//        }
//        if(filter.lastName() != null && !filter.lastName().isEmpty()){
//            spec = spec.and(UserSpecification.getByLastName(filter.lastName()));
//        }
//        if(filter.minAge() != null && filter.maxAge() != null){
//            spec = spec.and(UserSpecification.getByAge(filter.minAge(), filter.maxAge()));
//        }
//
//        return userRepository.findAll(spec);
//    }

