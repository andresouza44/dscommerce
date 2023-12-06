package com.projetos.dscommerce.dto;

import com.projetos.dscommerce.entities.Category;
import com.projetos.dscommerce.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min=3, max = 80, message = "O Nome precisa ter entre 3 e 80 caracteres")
    private String name;


    @NotBlank(message = "Campo Requerido")
    @Size(min=10, message = "A Descrição não pode ter menos que 10 caracteres")
    private String description;

    @Positive (message = "O preço deve ser positivo")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Deve ter pelo menos uma categoria ")
    List<CategoriesDTO> categories = new ArrayList<>();

    public ProductDTO(){

    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        for (Category cat : entity.getCategories()){
            categories.add(new CategoriesDTO(cat));
        }
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoriesDTO> getCategories() {
        return categories;
    }
}
