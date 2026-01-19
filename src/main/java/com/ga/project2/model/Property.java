package com.ga.project2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "properties")
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    private String name;
    private String description;
    private Double price;
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

    @JoinColumn(name = "image_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PropertySchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<PropertySchedule> schedules) {
        this.schedules = schedules;
    }

    public List<PropertyBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<PropertyBooking> bookings) {
        this.bookings = bookings;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}