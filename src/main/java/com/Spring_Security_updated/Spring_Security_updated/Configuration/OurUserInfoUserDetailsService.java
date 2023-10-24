package com.Spring_Security_updated.Spring_Security_updated.Configuration;


import com.Spring_Security_updated.Spring_Security_updated.Entity.OurUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.Spring_Security_updated.Spring_Security_updated.Repository.OurUserRepository;
import java.util.Optional;



@Configuration
public class OurUserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private OurUserRepository OurUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<OurUser> user = OurUserRepository.findByEmail(username);
        return user.map( OurUserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User does not Found !"));
    }
}
