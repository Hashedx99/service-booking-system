package com.ga.project2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "property_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;   // PK

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false) // FK to Property
    private Property property;
}
