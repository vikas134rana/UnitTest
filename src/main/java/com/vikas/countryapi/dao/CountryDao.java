package com.vikas.countryapi.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CountryDao {

    List<String> allCountries = Arrays.asList("India", "Indonesia", "China", "Pak", "Ban", "Italy", "Germany", "France");

    public List<String> getAllCountriesStartWith(String s) {
        return allCountries.stream()
                .filter(c -> c.startsWith(s)).collect(Collectors.toList());
    }

    public boolean containsCountries(List<String> countries) {
        return new HashSet<>(allCountries).containsAll(countries);
    }

    public String getFirstCountryStartsWith(String s){
        return allCountries.stream()
                .filter(c -> c.startsWith(s)).findFirst().orElse(null);
    }
}
