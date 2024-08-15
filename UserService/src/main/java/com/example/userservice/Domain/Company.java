package com.example.userservice.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Company")
public class Company extends Users {
    private String socialName;
    private String professionalField;
    private String description;
    private Long cnpj;

    @ElementCollection
    @CollectionTable(name = "company_expertise", joinColumns = @JoinColumn(name = "company_id"))
    private List<String> expertise = new ArrayList<>();

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
