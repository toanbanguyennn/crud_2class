package com.example.crud_province.service;

import com.example.crud_province.common.ICRUDService;
import com.example.crud_province.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProvinceService extends ICRUDService<Province> {
    List<Province> findBySearch(String name);
    Page<Province> findAll(Pageable pageable);
}
