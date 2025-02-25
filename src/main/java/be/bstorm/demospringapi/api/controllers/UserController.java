package be.bstorm.demospringapi.api.controllers;

import be.bstorm.demospringapi.api.models.security.dtos.UserDTO;
import be.bstorm.demospringapi.bll.models.forms.user.UserFilter;
import be.bstorm.demospringapi.bll.services.UserService;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

//    @GetMapping
//    public ResponseEntity<List<UserDTO>> getAllUsers(
//            @ModelAttribute UserFilter filter
//            ) {
//        List<UserDTO> users = userService.getUsers(filter).stream()
//                .map(UserDTO::fromUser)
//                .toList();
//        return ResponseEntity.ok(users);
//    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam Map<String, String> params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "lastName") String sort

    ) {
        List<SearchParam<User>> searchParams = SearchParam.create(params);
        List<UserDTO> users = userService.getUsers(
                        searchParams,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))
                ).stream()
                .map(UserDTO::fromUser)
                .toList();
        return ResponseEntity.ok(users);
    }
}
