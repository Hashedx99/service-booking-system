package com.ga.project2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // FK's:
//    @JoinColumn(name = "schedule_id")
//    private Long scheduleId;

    @JsonIgnore // do not print the user details
    @ManyToOne(fetch = FetchType.LAZY) // do not fetch the user details when fetching a service
    @JoinColumn(name = "provider_id", nullable = false)
    private User user;
}
