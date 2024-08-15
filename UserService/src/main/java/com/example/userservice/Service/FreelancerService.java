package com.example.userservice.Service;

import com.example.userservice.Domain.Freelancer;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FreelancerService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private FreelancerRepository freelancerRepository;

    public Freelancer save(Freelancer freelancer){
        freelancer.setRole(Roles.FREELANCER);
        freelancer.setPassword(bCryptPasswordEncoder.encode(freelancer.getPassword()));
        freelancerRepository.save(freelancer);
        return freelancer;
    }

    public List<Freelancer> listAll(){
        return freelancerRepository.findAll();
    }

    public Freelancer getById(Long id) throws Exception{
        Optional<Freelancer> freelancer = freelancerRepository.findById(id);
        if(freelancer.isPresent()){
            return freelancer.get();
        }else {
            throw new Exception("User not found");
        }
    }

    public Freelancer update(Freelancer freelancer, Long id) throws Exception {
        Optional<Freelancer> optionalFreelancer = freelancerRepository.findById(id);
        if (optionalFreelancer.isPresent()) {
            Freelancer storedFreelancer = optionalFreelancer.get();
            freelancer.setId(storedFreelancer.getId());
            freelancer.setPassword(storedFreelancer.getPassword());
            freelancer.setRole(storedFreelancer.getRole());
            freelancer.setCreatedAt(storedFreelancer.getCreatedAt());
            freelancer.setEnabled(storedFreelancer.isEnabled());
            freelancerRepository.save(freelancer);
            return freelancer;
        } else {
            throw new Exception("User not found");
        }
    }

    public List<String> listExpertises(Long id) throws Exception{
        Optional<Freelancer> optionalFreelancer = freelancerRepository.findById(id);
        if(optionalFreelancer.isPresent()){
            return optionalFreelancer.get().getExpertise();
        } else {
            throw new Exception("User not found");
        }
    }

    public List<String> addExpertise(List<String> expertises,Long id){
        Optional<Freelancer> optionalFreelancer = freelancerRepository.findById(id);
        if(optionalFreelancer.isPresent()){
            Freelancer storedFreelancer = optionalFreelancer.get();
            Set<String> currentExpertises = new HashSet<>(storedFreelancer.getExpertise());
            List<String> updatedExpertises = new ArrayList<>(currentExpertises);
            for (String expertise : expertises) {
                if (currentExpertises.add(expertise)) {
                    updatedExpertises.add(expertise);
                }
            }
            storedFreelancer.setExpertise(updatedExpertises);
            freelancerRepository.save(storedFreelancer);
        }
        return expertises;
    }

    public List<String> removeExpertise(Integer expertisesIndex,Long id) throws Exception{
        Optional<Freelancer> optionalFreelancer = freelancerRepository.findById(id);
        if(optionalFreelancer.isPresent()){
            Freelancer storedFreelancer = optionalFreelancer.get();
            storedFreelancer.removeExpertise(expertisesIndex);
            freelancerRepository.save(storedFreelancer);
            return storedFreelancer.getExpertise();
        }else{
            throw new Exception("User not found");
        }
    }
}
