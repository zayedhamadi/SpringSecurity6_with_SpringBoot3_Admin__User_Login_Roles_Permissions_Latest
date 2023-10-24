package com.Spring_Security_updated.Spring_Security_updated.Repository;

import com.Spring_Security_updated.Spring_Security_updated.Entity.OurUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OurUserRepository extends JpaRepository<OurUser,Integer> {
        @Query(value="select * from _ouruser where email=?1 ",nativeQuery = true)
        Optional<OurUser>findByEmail(String email);

}
/*  Cela signifie que la requête SQL que vous fournissez sera exécutée directement sur la base de données sans être analysée ou modifiée par Hibernate.*/
