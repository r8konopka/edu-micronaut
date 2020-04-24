package com.softwarehut.edu.user;

import java.util.Optional;

import javax.inject.Singleton;

@Singleton
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    Iterable<User> findAll() {
        return repository.findAll();
    }

    Optional<User> findById(final Long id) {
        return repository.findById(id);
    }

    void add(final User user) {
        repository.save(user);
    }

    void delete(final Long id) {
        repository.deleteById(id);
    }
}
