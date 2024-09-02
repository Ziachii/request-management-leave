/*
package com.my_project.API.management_system.config.security;

import com.my_project.API.management_system.entity.User;
import com.my_project.API.management_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class OurUserInfoUserDetailSevice implements UserDetailsService {

    private UserRepository  ourUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= ourUserRepository.findByEmail(username);
        return user.map(u ->new OurUserInfoUserDetail(u.getEmail(),u.getPassword()))
                .orElseThrow(()->new UsernameNotFoundException("User Does not Exist"));
    }
}
*/
