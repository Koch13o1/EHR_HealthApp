package com.kochhealth.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String role; // "SENIOR" or "CAREGIVER"

    @ManyToOne
    @JoinColumn(name="caregiver_id")
    private User caregiver;

    @JsonIgnore
    @OneToMany(mappedBy = "caregiver")
    private List<User> seniors;
}

