package com.toolrental.toolrentalproject.brand;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Optional<Brand> findByName(String name);

    void deleteByName(String name);
}
