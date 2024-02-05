package com.example.springSecurityJwtSem7.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 */
public interface UserDetailService {
    /**
     * принимает имя пользователя и отыскивает объект UserDetails
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
