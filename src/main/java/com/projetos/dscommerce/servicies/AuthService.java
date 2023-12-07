package com.projetos.dscommerce.servicies;

import com.projetos.dscommerce.entities.User;
import com.projetos.dscommerce.servicies.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    public void validateSelfOrAdmin (Long userId){
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)){
            System.out.println("Role: " + me.hasRole("ROLE_ADMIN"));
            System.out.println("Id: " + me.getId() +" "+ me.getId().equals(userId));
            throw new ForbiddenException("Acess denied");

        }
    }
}
