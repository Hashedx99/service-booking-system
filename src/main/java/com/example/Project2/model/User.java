package com.example.Project2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"password","userProfile","categoryList","recipeList"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String userName;
    @Column(unique = true)
    private String emailAddress;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @OneToOne(
            cascade = CascadeType.ALL,fetch = FetchType.LAZY
    )
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    private UserProfile userProfile;

    private boolean accountVerified;

    @OneToMany(mappedBy = "user" , orphanRemoval = true)
    @JsonIgnore
    private List<Property> properties;

    @JsonIgnore
    public String getPassword(){
        return password;
    }

}
