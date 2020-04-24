package com.softwarehut.argon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class ArgonSpringTest {

    @Test
    void passwordShouldMatchesHash() {
        //given
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 12, 3);
        StringBuilder password = new StringBuilder("qwerty123");

        //when - encode password
        String hash = encoder.encode(password);

        //then
        Assertions.assertTrue(encoder.matches(password, hash));
    }
}
