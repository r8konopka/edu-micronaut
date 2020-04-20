package com.softwarehut.edu;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class CompanyService {

    private final List<Company> companies = List.of(
            new Company(0, "SoftwareHut" , "Systemy informatyczne", "https://softwarehut.com"),
            new Company(1, "Zonifero" , "Office helper app", "https://zonifero.com"),
            new Company(2, "ExtraHut" , "Kompetencje, Usługi, Zespół", "https://extrahut.com"),
            new Company(3, "LegalHut" , "Usługi, Przetargi IT", "https://legalhut.com"),
            new Company(4, "Solution4Labs" , "Zarządzanie laboriatoriami", "https://solution4labs.com"),
            new Company(5, "TenderHut" , "Startupy, Projekty UE, Zarządzanie spółkami zależnymi", "https://tenderhut.com"),
            new Company(6, "ProtectHut" , "Cyversecurity, Analizy i testy, Audyt", "https://protecthut.com"));

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
        companies.add(company);
    }
}
