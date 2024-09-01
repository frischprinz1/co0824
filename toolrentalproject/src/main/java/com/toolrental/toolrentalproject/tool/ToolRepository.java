package com.toolrental.toolrentalproject.tool;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ToolRepository extends CrudRepository<Tool, Integer> {
    Optional<Tool> findByToolCode(String toolCode);

    void deleteByToolCode(String toolCode);

    Boolean existsByToolCode(String toolCode);
}
