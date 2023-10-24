package com.Spring_Security_updated.Spring_Security_updated.Repository;

import com.Spring_Security_updated.Spring_Security_updated.Entity.OurUser;
import com.Spring_Security_updated.Spring_Security_updated.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product,Integer> {
    @Query(value="select * from _product where email=?1 ",nativeQuery = true)
    Optional<OurUser> findByEmail(String email);

}

