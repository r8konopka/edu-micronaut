package com.softwarehut.edu.user;

import java.util.Map;
import java.util.stream.StreamSupport;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.convert.format.MapFormat;

@ConfigurationProperties("credentials")
public class UsersStore {

    UserService service;

    public UsersStore(UserService service) {
        this.service = service;
    }

    @MapFormat
    Map<String, String> users;
    @MapFormat
    Map<String, String> roles;
    public String getUserPassword(String username) {
        StreamSupport.stream(service.findAll().spliterator(), false)
                .forEach(e -> {
                    users.put(e.getLogin(), e.getPassword());
                });
        return users.get(username);
    }
    public String getUserRole(String username) {
        return roles.get(username);
    }
}