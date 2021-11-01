package ar.com.ada.api.noaa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.repos.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository repo;
    

    public void crear(Boya boya){
        repo.save(boya);
    }

    public List<Boya> traerBoyas() {
        return repo.findAll();
    }

    public Boya traerById(Integer boyaId) {
        return repo.findByBoyaId(boyaId);
    }

    public List<Boya> traerByColor(String color) {
        return repo.findByColorLuz(color);
    }

    public boolean validarLatitudYLongitud(Boya boya) {
        
        Double latitud = boya.getLatitudInstalacion();
        Double longitud = boya.getLongitudInstalacion();
        
        if (latitud > 90 || latitud < -90) {
			return false;
		} else if (longitud > 180 || longitud < -180) {
			return false;
		} else {
            return true;
        }

    }
}
