package ar.com.ada.api.noaa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.models.request.ActualizacionBoyaRequest;
import ar.com.ada.api.noaa.models.request.NuevaBoyaInfoRequest;
import ar.com.ada.api.noaa.models.response.GenericResponse;
import ar.com.ada.api.noaa.services.BoyaService;


@RestController
public class BoyaController {

    @Autowired
    BoyaService service;

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> crear(@RequestBody NuevaBoyaInfoRequest nuevaBoyaInfo) {

        GenericResponse r = new GenericResponse();

        Boya nuevaBoya = new Boya();
        nuevaBoya.setLongitudInstalacion(nuevaBoyaInfo.longitudInstalacion);
        nuevaBoya.setLatitudInstalacion(nuevaBoyaInfo.latitudInstalacion);
        nuevaBoya.setColorLuz("AZUL");
        
        service.crear(nuevaBoya);
        
        r.isOk = true;
        r.id = nuevaBoya.getBoyaId();
        r.message = "Boya creada con exito.";

        
        return ResponseEntity.ok(r);
    }

    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> traerBoyas(){

        return ResponseEntity.ok(service.traerBoyas());

    }

    @GetMapping("/boyas/{boyaId}")
    public ResponseEntity<?> traerById(@PathVariable Integer boyaId){

        if(service.traerById(boyaId) == null) {

            GenericResponse r = new GenericResponse();
            r.isOk = false;
            r.message = "El id ingresado no corresponde a ninguna boya.";

            return ResponseEntity.badRequest().body(r);

        } else {

            return ResponseEntity.ok(service.traerById(boyaId));

        }
    }

    @PutMapping("/boyas/{boyaId}")
    public ResponseEntity<?> actualizar(@PathVariable Integer boyaId, @RequestBody ActualizacionBoyaRequest actualizacionBoya){

        GenericResponse r = new GenericResponse();

        Boya boya = service.traerById(boyaId);
        boya.setColorLuz(actualizacionBoya.color);
        service.crear(boya);

        r.isOk = true;
        r.id = boya.getBoyaId();
        r.message = "Boya actualizada con exito";

        return ResponseEntity.ok(r);

        
    }
}
