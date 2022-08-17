package com.example.crud_province.service.impl;

import com.example.crud_province.model.Country;
import com.example.crud_province.repository.ICountryRepository;
import com.example.crud_province.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CountryService implements ICountryService {

    @Autowired
    private ICountryRepository iCountryRepository;

    @Override
    public Country save(Country country) {
        return iCountryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        iCountryRepository.deleteById(id);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return iCountryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return iCountryRepository.findAll();
    }
}
