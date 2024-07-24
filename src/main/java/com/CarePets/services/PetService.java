package com.CarePets.services;


import com.CarePets.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    IPetRepository iPetRepository;


    public void deletePet(Long id){
        iPetRepository.deleteById(id);
    }
}
