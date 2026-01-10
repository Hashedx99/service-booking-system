package com.example.Project2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString()
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String profileDescription;
    @JsonIgnore
    @OneToOne(mappedBy = "userProfile",fetch = FetchType.EAGER)
    private User user;


}

