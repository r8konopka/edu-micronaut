package com.softwarehut.edu;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Company {

    private final int id;
    private final String name;
    private final String description;
    private final String website;

}
