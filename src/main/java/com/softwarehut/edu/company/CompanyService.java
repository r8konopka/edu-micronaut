package com.softwarehut.edu.company;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class CompanyService {

    private final CompanyRepository repository;

    private final List<Company> companies = List.of(
            new Company(0l, "SoftwareHut" , "Systemy informatyczne", "https://softwarehut.com"),
            new Company(1l, "Zonifero" , "Office helper app", "https://zonifero.com"),
            new Company(2l, "ExtraHut" , "Kompetencje, Usługi, Zespół", "https://extrahut.com"),
            new Company(3l, "LegalHut" , "Usługi, Przetargi IT", "https://legalhut.com"),
            new Company(4l, "Solution4Labs" , "Zarządzanie laboriatoriami", "https://solution4labs.com"),
            new Company(5l, "TenderHut" , "Startupy, Projekty UE, Zarządzanie spółkami zależnymi", "https://tenderhut.com"),
            new Company(6l, "ProtectHut" , "Cyversecurity, Analizy i testy, Audyt", "https://protecthut.com"));

    public CompanyService(CompanyRepository companyRepository) {
        this.repository = companyRepository;
    }

    List<Company> getAll() {
        return companies;
    }

    Company findByName(final String name) {
        return companies.stream()
                .filter(e -> e.getName().toLowerCase().contains(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Company with given name does not exist."));
    }

    void add(final Company company) {
        repository.save(company);
    }
}
