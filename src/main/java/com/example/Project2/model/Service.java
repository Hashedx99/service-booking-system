package com.example.Project2.model;

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

    // FK's:
//    @JoinColumn(name = "schedule_id")
//    private Long scheduleId;

    @JoinColumn(name = "provider_id")
    private Long providerId;
}
