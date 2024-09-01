package com.toolrental.toolrentalproject.tooltype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/tooltypes")
public class ToolTypeController {
    @Autowired
    private ToolTypeService toolTypeService;

    @GetMapping(path = "/{prefix}")
    public Optional<ToolType> getOne(@PathVariable("prefix") String prefix) {
        return toolTypeService.getOne(prefix);
    }

    @GetMapping()
    public Iterable<ToolType> getCollection() {
        return toolTypeService.getAll();
    }

    @PostMapping(path = "/add")
    public ToolType addOne(@RequestBody ToolType toolType) {
        return toolTypeService.add(toolType);
    }

    @PostMapping(path = "/addCollection")
    public Iterable<ToolType> addCollection(@RequestBody Iterable<ToolType> toolTypes) {
        return toolTypeService.addAll(toolTypes);
    }

    @DeleteMapping(path = "/{id}/delete")
    public void removeOne(@PathVariable("id") String id) {
        toolTypeService.remove(id);
    }

    @DeleteMapping(path = "/delete")
    public void removeCollection() {
        toolTypeService.removeAll();
    }

    @PutMapping(path = "/update")
    public Optional<ToolType> updateOne(@PathVariable("id") String id,
            @RequestBody ToolType newToolType) {

        return toolTypeService.update(id, newToolType);
    }
}
