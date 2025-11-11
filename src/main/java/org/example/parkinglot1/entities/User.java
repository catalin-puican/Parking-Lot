package org.example.parkinglot1.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private Set<Car> cacrs = new LinkedHashSet<>();

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public Set<Car> getCacrs() {
        return cacrs;
    }

    public void setCacrs(Set<Car> cacrs) {
        this.cacrs = cacrs;
    }
}