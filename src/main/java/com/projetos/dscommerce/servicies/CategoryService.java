package com.projetos.dscommerce.servicies;

import com.projetos.dscommerce.dto.CategoryDTO;
import com.projetos.dscommerce.entities.Category;
import com.projetos.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll(){
        List<Category> result = repository.findAll();
        return result.stream().map( e -> new CategoryDTO(e)).toList();
    }
}
