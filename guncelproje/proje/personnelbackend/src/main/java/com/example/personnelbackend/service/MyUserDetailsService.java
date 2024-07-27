package com.example.personnelbackend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("berkay".equals(username)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername("berkay") // kullanıcı adı
                    .password("{noop}8442") // {noop} şifreyi şifrelemeden kabul eder
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
        }
    }
}