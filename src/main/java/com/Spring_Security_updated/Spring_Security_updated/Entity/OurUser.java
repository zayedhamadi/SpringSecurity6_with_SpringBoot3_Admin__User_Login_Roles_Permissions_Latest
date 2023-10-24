package com.Spring_Security_updated.Spring_Security_updated.Entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@Entity
@Table(name = "_ouruser")
@NoArgsConstructor
@AllArgsConstructor
public class OurUser   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String email;
    private String password;
    private String Role;


}
