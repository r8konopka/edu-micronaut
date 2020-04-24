package com.softwarehut.argon;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class ArgonTest {

    @Test
    void passwordShouldMatchesHash() {
        //given
        final String password = "qwerty123";
        char[] passwordChars = password.toCharArray();
        //one of 2 available versions of Argon2
        final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);

        //encode password
        final String hash = argon2.hash(2, 97656, 1, passwordChars);

        Assertions.assertTrue(argon2.verify(hash, passwordChars));
    }
}
