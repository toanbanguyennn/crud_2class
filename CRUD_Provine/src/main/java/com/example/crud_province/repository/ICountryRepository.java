package com.example.crud_province.repository;

import com.example.crud_province.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository  extends JpaRepository<Country, Long> {
}
