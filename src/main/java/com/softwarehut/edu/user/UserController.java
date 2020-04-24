package com.softwarehut.edu.user;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;

@Secured("isAuthenticated()")
@Controller("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Post
    void create(@Body User user) {
        userService.add(user);
    }

    @Get
    Iterable<User> findAll() {
        return userService.findAll();
    }

    @Put
    void udpate() {}

    @Delete(uri = "/{id}")
    void delete(Long id) {
        userService.delete(id);
    }
}
