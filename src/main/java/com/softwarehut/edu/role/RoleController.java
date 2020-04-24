package com.softwarehut.edu.role;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;

@Secured("isAuthenticated()")
@Controller("/role")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @Post
    void create(@Body Role role) {
        service.add(role);
    }

    @Get
    Iterable<Role> findAll() {
        return service.getAll();
    }

    @Put
    void udpate() {}

    @Delete
    void delete() {}
}
