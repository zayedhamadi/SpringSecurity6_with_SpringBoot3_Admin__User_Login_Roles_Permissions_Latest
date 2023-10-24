package com.Spring_Security_updated.Spring_Security_updated.Controller;


import com.Spring_Security_updated.Spring_Security_updated.Configuration.SecurityConfig;
import com.Spring_Security_updated.Spring_Security_updated.Entity.OurUser;
import com.Spring_Security_updated.Spring_Security_updated.Repository.OurUserRepository;
import com.Spring_Security_updated.Spring_Security_updated.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private OurUserRepository ourUserRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityConfig securityConfig;


    @GetMapping("/")
    public String goHome() {
        return "this a publickly accessible withinn needing  authentication required ";
    }

    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUser(@RequestBody OurUser OurUser) {
        OurUser.setPassword(passwordEncoder.encode(OurUser.getPassword()));
        OurUser r = ourUserRepository.save(OurUser);
        if (r.getId() > 0) {
            return ResponseEntity.ok("User saved successfully!");
        } else return ResponseEntity.status(404).body("Error,Failed to save User!");

    }

    @GetMapping("/product/all")
    public ResponseEntity<Object> getAllProduct() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAlUser() {
        return ResponseEntity.ok(ourUserRepository.findAll());
    }

    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getUserDetails() {
        return ResponseEntity.ok(ourUserRepository.findByEmail(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }


}
