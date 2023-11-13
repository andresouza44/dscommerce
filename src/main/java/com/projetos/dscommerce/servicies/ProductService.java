package com.projetos.dscommerce.servicies;

import com.projetos.dscommerce.dto.ProductDTO;
import com.projetos.dscommerce.entities.Product;
import com.projetos.dscommerce.repositories.ProductRepository;
import com.projetos.dscommerce.servicies.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product =  repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Resource not Found with id " + id));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result= repository.findAll(pageable);
        return result.map(e -> new ProductDTO(e)); // Page já um stream
    }

    @Transactional
    public ProductDTO insert( ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e ){
            throw  new ResourceNotFoundException("Resource not found");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not Found");
        }
        try {
            repository.deleteById(id);

        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Data Integrity Violation Excpetion");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

    }

}

