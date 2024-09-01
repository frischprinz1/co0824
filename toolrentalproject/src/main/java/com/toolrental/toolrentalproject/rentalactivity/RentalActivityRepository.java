package com.toolrental.toolrentalproject.rentalactivity;

import org.springframework.data.repository.CrudRepository;

public interface RentalActivityRepository extends CrudRepository<RentalActivity, Integer> {

    // Optional<RentalActivity> findByTool(Tool toolCode);
}
