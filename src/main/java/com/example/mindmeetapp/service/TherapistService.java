package com.example.mindmeetapp.service;

import com.example.mindmeetapp.model.TherapistModel;
import com.example.mindmeetapp.repository.TherapsitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TherapistService {

    private final TherapsitRepository repo;

    public List<TherapistModel> getTherapist(){
        return repo.findAll();
    }
    public void createTherapist(TherapistModel model) throws Exception{
        try{
            repo.save(model);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTherapist(TherapistModel model){
        repo.save(model);
    }
    public void deleteTherapist(Long id){
        repo.deleteById(id);
    }
    public TherapistModel getTherapistById(Long id){
        return repo.findById(id).orElse(null);
    }

    //TherapistService
    //createTherapist
    //updateTherapist
    //deleteTherapist
    //getTherapistById
}
