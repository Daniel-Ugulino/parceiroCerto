package com.example.userservice.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Freelancer")
public class Freelancer extends Users {
    private String description;

    @ElementCollection
    @CollectionTable(name = "freelancer_especialidades", joinColumns = @JoinColumn(name = "freelancer_id"))
    private List<String> expertise;

    public List<String> addExpertise(String expertise) {
        this.expertise.add(expertise);
        return this.expertise;
    }

    public List<String> removeExpertise(Integer index) {
        if (index >= 0 && index < this.expertise.size()) {
            this.expertise.remove((int) index);
        }
        return this.expertise;
    }
}
