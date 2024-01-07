package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Data
public class User  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;
    @Column(name = "password")
    private String password ;

    private int IsActive ;

    @Column(name = "date_of_birth")
    private Date dateOfBirth ;
    @Column(name = "facebook_account_id")
    private int  facebookAccountId ;
    @Column(name = "google_account_id")
    private int googleAccountId ;
    @ManyToOne
    @JoinColumn (name = "role_id")
    private Role role;

}
