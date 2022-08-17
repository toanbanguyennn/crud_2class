package com.example.crud_province.service.impl;

import com.example.crud_province.model.Province;

import com.example.crud_province.repository.IProvinceRepository;
import com.example.crud_province.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ProvinceService implements IProvinceService {
    @Autowired
    public IProvinceRepository iProvinceRepository;

    @Override
    public Province save(Province province) {
        return iProvinceRepository.save(province);
    }

    @Override
    public void delete(Long id) {
        iProvinceRepository.deleteById(id);
    }

    @Override
    public Optional<Province> findById(Long id) {
        return iProvinceRepository.findById(id);
    }

    @Override
    public List<Province> findAll() {
        return iProvinceRepository.findAll();
    }

    @Override
    public List<Province> findBySearch(String name) {
        return iProvinceRepository.findByName("%" + name + "%");

    }
    @Override
    public Page<Province> findAll(Pageable pageable) {
        return iProvinceRepository.findAll(pageable);
    }
}
