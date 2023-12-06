package com.projetos.dscommerce.dto;

import com.projetos.dscommerce.entities.Category;

public class CategoriesDTO {

    private Long id;
    private String name;

    public  CategoriesDTO(){

    }

    public CategoriesDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoriesDTO (Category entity){
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
