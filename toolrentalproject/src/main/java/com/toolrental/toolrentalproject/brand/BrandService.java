package com.toolrental.toolrentalproject.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Optional<Brand> get(String name) {
        return brandRepository.findByName(name);
    }

    public Iterable<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    public Iterable<Brand> addAll(Iterable<Brand> brands) {
        return brandRepository.saveAll(brands);
    }

    public Optional<Brand> update(String brandName, Brand newBrand) {
        return brandRepository.findByName(brandName)
                .map(brand -> {
                    brandRepository.delete(brand);
                    return brandRepository.save(newBrand);
                });
    }

    public void removeOne(String brandName) {
        brandRepository.findByName(brandName)
                .map(brand -> {
                    brandRepository.delete(brand);
                    return null;
                });
    }

    public void removeAll() {
        brandRepository.deleteAll();
    }

}
