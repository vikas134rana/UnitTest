package com.vikas.countryapi.service;

import com.vikas.countryapi.dao.CountryDao;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CountryServiceTest {

    private CountryDao countryDao = Mockito.mock(CountryDao.class);
    private CountryService countryService = new CountryService(countryDao);

    @Test
    void getCountryStartsWith() {
        when(countryDao.getAllCountriesStartWith("I")).thenReturn(Arrays.asList("India", "Indonesia"));
        List<String> countries = countryService.getCountryStartsWith("I");

        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(countryDao).getAllCountriesStartWith(captor.capture());
        final String argument = captor.getValue();

        System.out.println(argument);
    }
}