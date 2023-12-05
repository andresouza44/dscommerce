package com.projetos.dscommerce.servicies;

import com.projetos.dscommerce.entities.Role;
import com.projetos.dscommerce.entities.User;
import com.projetos.dscommerce.projections.UserDetailsProjection;
import com.projetos.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0 ){
            throw  new UsernameNotFoundException("User no Found");
        }

        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        result.forEach(e -> user.addRole(new Role(e.getRoleId(), e.getAuthority())));

        return user;


    }
}
