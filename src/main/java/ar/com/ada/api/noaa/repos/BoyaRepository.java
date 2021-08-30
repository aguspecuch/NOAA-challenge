package ar.com.ada.api.noaa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.noaa.entities.Boya;

import java.util.*;

@Repository
public interface BoyaRepository extends JpaRepository<Boya, Integer>{
    
    Boya findByBoyaId(Integer id);
    List<Boya> findByColorLuz(String color);
}
