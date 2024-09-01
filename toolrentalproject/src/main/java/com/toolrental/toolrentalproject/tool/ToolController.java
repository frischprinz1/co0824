package com.toolrental.toolrentalproject.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tools")
public class ToolController {
    @Autowired
    private ToolService toolService;

    @PostMapping(path = "/add")
    public Tool addOne(@RequestParam(name = "toolType") String toolType,
            @RequestParam(name = "toolCode") String toolCode, @RequestParam(name = "brand") String brand) {

        return toolService.add(toolType, toolCode, brand);
    }

    @GetMapping(path = "/list")
    public Iterable<Tool> getAll() {
        return toolService.getAll();
    }

    @GetMapping(path = "/{toolCode}/list")
    public Tool getOne(@PathVariable("toolCode") String toolCode) {
        return toolService.get(toolCode);
    }

    @DeleteMapping(path = "/{toolCode}/delete")
    public Boolean removeOne(@PathVariable("toolCode") String toolCode) {
        return toolService.remove(toolCode);
    }

    @DeleteMapping(path = "/delete")
    public void removeAll() {
        toolService.removeAll();
    }

    @PutMapping(path = "/update")
    public Tool update(@PathVariable("toolCode") String toolCode,
            @RequestBody Tool newTool) {

        return toolService.editTool(toolCode, newTool);
    }
}
