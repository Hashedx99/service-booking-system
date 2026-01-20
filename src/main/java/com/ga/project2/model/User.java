package com.ga.project2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"password","userProfile"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    private String userName;
    @Column(unique = true)
    private String emailAddress;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @OneToOne(
            cascade = CascadeType.ALL,fetch = FetchType.EAGER
    )
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    private UserProfile userProfile;

    private boolean accountVerified;
    private boolean isActivated;

    @OneToMany(mappedBy = "user" , orphanRemoval = true , fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Property> properties;

    @JsonIgnore
    public String getPassword(){
        return password;
    }

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    private List<Service> services;

    private UserRoles role = UserRoles.CUSTOMER;

}
