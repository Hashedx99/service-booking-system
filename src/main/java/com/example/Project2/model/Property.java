package com.example.Project2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    private String name;
    private String description;
    private Double price;
    private String scheduleId;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // foreign key column
    private User user;

    //mappedBy must match the field name in PropertySchedule ("property")
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PropertySchedule> schedules;

    //mappedBy must match the field name in PropertyBooking ("property")
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PropertyBooking> bookings;

}