package com.blackstar.jobag.user.controller;

import com.blackstar.jobag.user.domain.model.User;
import com.blackstar.jobag.user.domain.service.UserService;
import com.blackstar.jobag.user.resource.SaveUserResource;
import com.blackstar.jobag.user.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Get Users", description="Get All Users", tags={"users"})
    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> userPage = userService.getAllUsers(pageable);
        List<UserResource> resources = userPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Update Users", description="Update Users", tags={"users"})
    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }

    @Operation(summary="Get UsersById", description="Get UsersById", tags={"users"})
    @GetMapping("/users/{userId}")
    public UserResource getUserById(@PathVariable(name = "id") Long userId) {
        return convertToResource(userService.getUserById(userId));
    }


    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }
    private UserResource convertToResource(User entity)
    {
        return mapper.map(entity, UserResource.class);
    }
}
