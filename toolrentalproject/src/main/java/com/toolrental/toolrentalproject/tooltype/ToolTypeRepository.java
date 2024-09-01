package com.toolrental.toolrentalproject.tooltype;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ToolTypeRepository extends CrudRepository<ToolType, Integer> {
    Optional<ToolType> findByPrefix(String prefix);

    void deleteByPrefix(String prefix);
}
