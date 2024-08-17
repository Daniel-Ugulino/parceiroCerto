package com.example.userservice.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Freelancer")
public class Freelancer extends Users {
    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelancer_especialidades", joinColumns = @JoinColumn(name = "freelancer_id"))
    private List<String> expertise = new ArrayList<>();

    public void addExpertise(List<String> expertises) {
        Set<String> currentExpertises = new HashSet<>(expertises);
        List<String> updatedExpertises = new ArrayList<>(currentExpertises);
        for (String expertise : expertises) {
            if (currentExpertises.add(expertise)) {
                updatedExpertises.add(expertise);
            }
        }
        this.setExpertise(updatedExpertises);
    }

    public void removeExpertise(Integer index) {
        if (index >= 0 && index < this.expertise.size()) {
            this.expertise.remove((int) index);
        }
    }
}
