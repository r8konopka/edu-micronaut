package com.softwarehut.edu;

import io.micronaut.core.annotation.TypeHint;
import io.micronaut.runtime.Micronaut;

@TypeHint(typeNames = {"org.postgresql.Driver", "org.postgresql.util.SharedTimer"})
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}