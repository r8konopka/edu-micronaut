package com.softwarehut.edu;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;

@Secured("isAuthenticated()")
@Controller("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @Get
    List<Company> getAll() {
        return companyService.getAll();
    }

    @Get(uri = "/{name}", produces = MediaType.APPLICATION_JSON)
    Company findByName(@PathVariable(name = "name") final String name) {
        return companyService.findByName(name);
    }

    @Post
    void add(@Body final Company company) {
        companyService.add(company);
    }
}
