package com.toolrental.toolrentalproject.tooltype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolrental.toolrentalproject.brand.Brand;
import com.toolrental.toolrentalproject.rate.RateRepository;
import java.util.Optional;

@Service
public class ToolTypeService {
    @Autowired
    private ToolTypeRepository toolTypeRepository;
    @Autowired
    private RateRepository rateRepository;

    public Optional<ToolType> getOne(String toolTypePrefix) {
        return toolTypeRepository.findByPrefix(toolTypePrefix);
    }

    public Iterable<ToolType> getAll() {
        return toolTypeRepository.findAll();
    }

    public ToolType add(ToolType newToolType) {
        rateRepository.save(newToolType.getRate());

        return toolTypeRepository.save(newToolType);
    }

    public Iterable<ToolType> addAll(Iterable<ToolType> newToolTypeCollection) {
        newToolTypeCollection.forEach(newToolType -> rateRepository.save(newToolType.getRate()));

        return toolTypeRepository.saveAll(newToolTypeCollection);
    }

    public void remove(String id) {
        toolTypeRepository.deleteByPrefix(id);
    }

    public void removeAll() {
        toolTypeRepository.deleteAll();
    }

    public Optional<ToolType> update(String id, ToolType newToolType) {
        return toolTypeRepository.findByPrefix(id)
                .map(toolType -> {
                    toolTypeRepository.delete(toolType);
                    return toolTypeRepository.save(newToolType);
                });
    }
}
