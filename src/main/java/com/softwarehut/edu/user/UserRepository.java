package com.softwarehut.edu.user;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface UserRepository extends CrudRepository<User, Long> {
}
