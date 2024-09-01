package com.toolrental.toolrentalproject.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping(path = "/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping(path = "/{brandName}")
    public Optional<Brand> getOne(@PathVariable("brandName") String brandName) {
        return brandService.get(brandName);
    }

    @GetMapping()
    public Iterable<Brand> getCollection() {
        return brandService.getAll();
    }

    @PostMapping(path = "/add")
    public Brand addOne(@RequestBody Brand brand) {
        return brandService.add(brand);
    }

    @PostMapping(path = "/addCollection")
    public Iterable<Brand> addCollection(@RequestBody Iterable<Brand> brands) {
        return brandService.addAll(brands);
    }

    @PutMapping(path = "/{brandName}/update")
    public Optional<Brand> updateOne(@PathVariable("brandName") String brandName, @RequestBody Brand brand) {
        return brandService.update(brandName, brand);
    }

    @DeleteMapping(path = "/{brandName}/delete")
    public void removeOne(@PathVariable("brandName") String brandName) {
        brandService.removeOne(brandName);
    }

    @DeleteMapping(path = "/delete")
    public void removeAll() {
        brandService.removeAll();
    }
}
