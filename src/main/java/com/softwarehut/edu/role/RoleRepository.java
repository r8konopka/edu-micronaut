package com.softwarehut.edu.role;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface RoleRepository extends CrudRepository<Role, Long> {
}
