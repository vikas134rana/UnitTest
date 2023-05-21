package com.vikas.countryapi.service;

import com.vikas.countryapi.dao.CountryDao;

import java.util.HashSet;
import java.util.List;

public class CountryService {

    private final CountryDao countryDao;

    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<String> getCountryStartsWith(String s) {
        return countryDao.getAllCountriesStartWith(s);
    }

    public boolean containsCountries(List<String> countries) {
        return countryDao.containsCountries(countries);
    }

    public String getFirstCountryStartsWith(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return countryDao.getFirstCountryStartsWith(s);
    }

}
