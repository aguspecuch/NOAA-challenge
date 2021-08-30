package ar.com.ada.api.noaa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.models.request.NuevaMuestraRequest;
import ar.com.ada.api.noaa.models.response.*;
import ar.com.ada.api.noaa.services.*;
import ar.com.ada.api.noaa.entities.*;

import java.util.*;

@RestController
public class MuestraController {

    @Autowired
    MuestraService service;

    @Autowired
    BoyaService boyaService;

    @PostMapping("/muestras")
    public ResponseEntity<MuestraResponse> crear(@RequestBody NuevaMuestraRequest nuevaMuestra) {

        Muestra muestra = new Muestra();
        muestra.setAlturaNivelMar(nuevaMuestra.alturaNivelDelMar);
        muestra.setHorarioMuestra(nuevaMuestra.horario);
        muestra.setLatitud(nuevaMuestra.latitud);
        muestra.setLongitud(nuevaMuestra.longitud);
        muestra.setAlturaNivelMar(nuevaMuestra.alturaNivelDelMar);
        muestra.setMatriculaEmbarcacion(nuevaMuestra.matricula);

        Boya boya = boyaService.traerById(nuevaMuestra.boyaId);
        boya.agregarMuestra(muestra);

        service.crear(muestra);

        if (muestra.getAlturaNivelMar() < -50 || muestra.getAlturaNivelMar() > 50) {
            boya.setColorLuz("AMARILLO");
        } else if (muestra.getAlturaNivelMar() < -100 || muestra.getAlturaNivelMar() > 100) {
            boya.setColorLuz("ROJO");
        } else {
            boya.setColorLuz("VERDE");
        }

        boyaService.crear(boya);

        MuestraResponse r = new MuestraResponse();
        r.id = muestra.getIdMuestra();
        r.color = boya.getColorLuz();

        return ResponseEntity.ok(r);
    }

    @GetMapping("muestras/boyas/{idBoya}")
    public ResponseEntity<?> traerMuestrasSegunBoya(@PathVariable Integer idBoya) {
        Boya boya = boyaService.traerById(idBoya);
        GenericResponse r = new GenericResponse();

        if (boya == null) {
            r.isOk = false;
            r.message = "El numero de id ingresado no coincide con ninguna boya";
            return ResponseEntity.badRequest().body(r);
        } else {
            return ResponseEntity.ok(service.traerMuestrasSegunBoya(idBoya));
        }
    }

    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<GenericResponse> delete(@PathVariable Integer id) {
        Muestra muestra = service.traerById(id);
        GenericResponse r = new GenericResponse();

        if (muestra == null) {

            r.isOk = false;
            r.message = "El id ingresado no corresponse a ninguna muestra";
            return ResponseEntity.badRequest().body(r);

        } else {

            Boya boya = boyaService.traerById(muestra.getBoya().getBoyaId());
            boya.setColorLuz("AZUL");
            boyaService.crear(boya);

            r.isOk = true;
            r.id = boya.getBoyaId();
            r.message = "Boya modificada con exito.";
            return ResponseEntity.ok(r);
        }
    }

    @GetMapping("/muestras/colores/{color}")
    public ResponseEntity<List<MuestraResponseSegunColor>> traerSegunColor(@PathVariable String color){

        List<Boya> resultado = boyaService.traerByColor(color);
        List<MuestraResponseSegunColor> lista = new ArrayList<>();
        MuestraResponseSegunColor m = new MuestraResponseSegunColor();
        for (Boya boya : resultado) {
            List<Muestra> listaMuestras = boya.getMuestras(); 
            for (Muestra muestra : listaMuestras) {
                m.boyaId = boya.getBoyaId();
                m.horario = muestra.getHorarioMuestra();
                m.alturaNivelDelMar = muestra.getAlturaNivelMar();
                lista.add(m);
            }    
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/muestras/minima/{idBoya}")
    public ResponseEntity<MuestraResponseMinima> traerMuestraMinima(@PathVariable Integer idBoya) {

        Boya boya = boyaService.traerById(idBoya);
        List<Muestra> lista = boya.getMuestras();
        MuestraResponseMinima m = new MuestraResponseMinima();
        m.alturaNivelDelMarMinima = 100.00;
        for (Muestra muestra : lista) {
            if (muestra.getAlturaNivelMar() < m.alturaNivelDelMarMinima) {
                m.alturaNivelDelMarMinima = muestra.getAlturaNivelMar();
                m.color = muestra.getBoya().getColorLuz();
                m.horario = muestra.getHorarioMuestra();
            }
        }

        return ResponseEntity.ok(m);
    }
}
