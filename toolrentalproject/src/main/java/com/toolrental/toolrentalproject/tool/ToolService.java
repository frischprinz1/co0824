package com.toolrental.toolrentalproject.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolrental.toolrentalproject.brand.Brand;
import com.toolrental.toolrentalproject.brand.BrandRepository;
import com.toolrental.toolrentalproject.exceptions.ToolNotFoundException;
import com.toolrental.toolrentalproject.tooltype.ToolType;
import com.toolrental.toolrentalproject.tooltype.ToolTypeRepository;

@Service
public class ToolService {
    @Autowired
    private ToolRepository toolRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ToolTypeRepository toolTypeRepository;

    public Tool add(String toolTypePrefix, String toolCode, String brandName) {
        Tool tool = new Tool();

        Brand newBrand = brandRepository.findByName(brandName).get();
        ToolType newToolType = toolTypeRepository.findByPrefix(toolTypePrefix).get();

        tool.setToolType(newToolType);
        tool.setToolCode(toolCode);
        tool.setBrand(newBrand);

        return toolRepository.save(tool);
    }

    public Tool get(String toolCode) {
        return toolRepository.findByToolCode(toolCode)
                .map(tool -> tool)
                .orElseThrow(() -> new ToolNotFoundException(toolCode));
    }

    public Iterable<Tool> getAll() {
        return toolRepository.findAll();
    }

    public Boolean remove(String toolCode) {
        toolRepository.deleteByToolCode(toolCode);

        return !toolRepository.existsByToolCode(toolCode);
    }

    public void removeAll() {
        toolRepository.deleteAll();
    }

    public Tool editTool(String toolCode, Tool newTool) {
        return toolRepository.findByToolCode(toolCode)
                .map(tool -> {
                    tool.setToolType(newTool.getToolType());
                    tool.setToolCode(newTool.getToolCode());
                    tool.setBrand(newTool.getBrand());
                    // tool.setQuantity(newTool.getQuantity());

                    return toolRepository.save(tool);
                })
                .orElseGet(() -> {
                    return toolRepository.save(newTool);
                });
    }
}
