package com.example.userservice.Service;

import com.example.userservice.Domain.Freelancer;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Dto.FreelancerDto;
import com.example.userservice.Dto.FreelancerUpdateDto;
import com.example.userservice.Repository.FreelancerRepository;
import org.springframework.beans.BeanUtils;
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

    public Freelancer save(FreelancerDto freelancerDto){
        Freelancer freelancerEntity = new Freelancer();
        BeanUtils.copyProperties(freelancerDto,freelancerEntity);
        freelancerEntity.setRole(Roles.FREELANCER);
        freelancerEntity.setPassword(bCryptPasswordEncoder.encode(freelancerEntity.getPassword()));
        freelancerRepository.save(freelancerEntity);
        return freelancerEntity;
    }

    public List<Freelancer> listAll(){
        return freelancerRepository.findAll();
    }

    public Freelancer getById(Long id) throws Exception{
        Optional<Freelancer> freelancer = freelancerRepository.findById(id);
        if(freelancer.isPresent()){
            return freelancer.get();
        }else {
            throw new Exception("Freelancer not found");
        }
    }

    public Freelancer update(FreelancerUpdateDto freelancerDto, Long id) throws Exception {
        Optional<Freelancer> optionalFreelancer = freelancerRepository.findById(id);
        if (optionalFreelancer.isPresent()) {
            Freelancer freelancerEntity = new Freelancer();
            BeanUtils.copyProperties(freelancerDto,freelancerEntity);
            Freelancer storedFreelancer = optionalFreelancer.get();
            freelancerEntity.reset(storedFreelancer);
            freelancerRepository.save(freelancerEntity);
            return freelancerEntity;
        } else {
            throw new Exception("Freelancer not found");
        }
    }

    public Freelancer addExpertise(List<String> expertises,Long id)throws Exception {
        Optional<Freelancer> optionalFreelancer = freelancerRepository.findById(id);
        if(optionalFreelancer.isPresent()){
            Freelancer storedFreelancer = optionalFreelancer.get();
            storedFreelancer.addExpertise(expertises);
            freelancerRepository.save(storedFreelancer);
            return storedFreelancer;
        }else{
            throw new Exception("Freelancer not found");
        }
    }

    public Freelancer removeExpertise(List<String> expertises,Long id) throws Exception {
        Optional<Freelancer> optionalFreelancer = freelancerRepository.findById(id);
        if (optionalFreelancer.isPresent()) {
            Freelancer storedFreelancer = optionalFreelancer.get();
            storedFreelancer.removeExpertise(expertises);
            freelancerRepository.save(storedFreelancer);
            return storedFreelancer;
        } else {
            throw new Exception("Freelancer not found");
        }
    }
}
