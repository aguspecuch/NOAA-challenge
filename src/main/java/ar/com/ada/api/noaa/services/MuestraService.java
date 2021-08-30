package ar.com.ada.api.noaa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.*;
import ar.com.ada.api.noaa.repos.MuestraRepository;

@Service
public class MuestraService {

    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;
    
    public void crear(Muestra muestra){
        repo.save(muestra);
    }

    public List<Muestra> traerMuestrasSegunBoya(Integer idBoya){
        
        Boya boya = boyaService.traerById(idBoya);

        return boya.getMuestras();
    }

    public Muestra traerById(Integer id){
        return repo.findByIdMuestra(id);
    }
}
