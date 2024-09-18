package com.example.userservice.Service;

import com.example.userservice.Audit.UserContext;
import com.example.userservice.Domain.Hirer;
import com.example.userservice.Domain.Roles;
import com.example.userservice.Dto.HirerDto;
import com.example.userservice.Dto.HirerUpdateDto;
import com.example.userservice.Repository.HirerRepository;
import com.example.userservice.Utils.ValidateCpfCnpj;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HirerService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private HirerRepository hirerRepository;

    public Hirer save(HirerDto hirerDto) throws Exception{
        Hirer hirerEntity = new Hirer();
        if(ValidateCpfCnpj.validateCpf(hirerDto.getCpf())){
            BeanUtils.copyProperties(hirerDto,hirerEntity);
            hirerEntity.setRole(Roles.HIRER);
            hirerEntity.setPassword(bCryptPasswordEncoder.encode(hirerEntity.getPassword()));
            UserContext.setUserId(hirerEntity.getEmail());
            hirerRepository.save(hirerEntity);
            return hirerEntity;
        }else {
            throw new Exception("cpf not valid");
        }
    }

    public List<Hirer> listAll(){
        return hirerRepository.findAll();
    }

    public Hirer getById(Long id) throws Exception{
        Optional<Hirer> hirer = hirerRepository.findById(id);
        if(hirer.isPresent()){
            return hirer.get();
        }else {
            throw new Exception("User not found");
        }
    }

    public Hirer update(HirerUpdateDto hirerDto, Long id) throws Exception {
        Optional<Hirer> optionalHirer = hirerRepository.findById(id);
        if (optionalHirer.isPresent()) {
            Hirer hirerEntity = new Hirer();
            BeanUtils.copyProperties(hirerDto,hirerEntity,"cpf");
            Hirer storedHirer = optionalHirer.get();
            hirerEntity.reset(storedHirer);
            hirerRepository.save(hirerEntity);
            return hirerEntity;
        } else {
            throw new Exception("User not found");
        }
    }

}
