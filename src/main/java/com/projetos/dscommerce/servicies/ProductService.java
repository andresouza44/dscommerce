package com.projetos.dscommerce.servicies;

import com.projetos.dscommerce.dto.ProductDTO;
import com.projetos.dscommerce.entities.Product;
import com.projetos.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product =  repository.findById(id).get();
        return new ProductDTO(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result= repository.findAll(pageable);
        return result.map(e -> new ProductDTO(e)); // Page já um stream
    }
}

