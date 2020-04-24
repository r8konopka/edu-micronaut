package com.softwarehut.edu.role;

import javax.inject.Singleton;

@Singleton
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    Iterable<Role> getAll() {
        return repository.findAll();
    }

    void add(final Role role) {
        repository.save(role);
    }
}
