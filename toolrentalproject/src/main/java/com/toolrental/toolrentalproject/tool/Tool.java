package com.toolrental.toolrentalproject.tool;

import com.toolrental.toolrentalproject.brand.Brand;
import com.toolrental.toolrentalproject.tooltype.ToolType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String toolCode;

    @OneToOne
    private Brand brand;

    @ManyToOne
    private ToolType toolType;

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolCode() {
        return toolCode;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    public Integer getId() {
        return id;
    }
}
