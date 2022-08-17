package com.example.crud_province.repository;

import com.example.crud_province.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findAllByNameContaining(String name);

    @Query(value = "select * from province where name like :name", nativeQuery = true)
    List<Province> findByName(@Param("name") String name);
}
