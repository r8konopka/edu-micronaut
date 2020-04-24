package com.softwarehut.edu.company;

import java.util.List;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured("isAuthenticated()")
@Controller("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @Get
    @Secured("VIEW")
    List<Company> getAll() {
        return companyService.getAll();
    }

    @Get("/public")
    @Secured(SecurityRule.IS_ANONYMOUS)
    List<Company> getAllPublic() {
        return companyService.getAll();
    }

    @Get(uri = "/{name}", produces = MediaType.APPLICATION_JSON)
    @Secured("ADMIN")
    Company findByName(@PathVariable(name = "name") final String name) {
        return companyService.findByName(name);
    }

    @Post
    void add(@Body final Company company) {
        companyService.add(company);
    }
}
