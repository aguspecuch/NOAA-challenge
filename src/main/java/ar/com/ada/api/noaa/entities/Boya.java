package ar.com.ada.api.noaa.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

@Entity
@Table( name = "boya")
public class Boya {
    
    @Id
    @Column( name = "boya_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer boyaId;

    @Column( name = "color_luz")
    private String colorLuz;

    @Column( name = "longitud_instalacion")
    private Double longitudInstalacion;

    @Column( name = "latitud_instalacion")
    private Double latitudInstalacion;

    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Muestra> muestras;
    
    public Integer getBoyaId() {
        return boyaId;
    }
    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }
    public String getColorLuz() {
        return colorLuz;
    }
    public void setColorLuz(String colorLuz) {
        this.colorLuz = colorLuz;
    }
    public Double getLongitudInstalacion() {
        return longitudInstalacion;
    }
    public void setLongitudInstalacion(Double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }
    public Double getLatitudInstalacion() {
        return latitudInstalacion;
    }
    public void setLatitudInstalacion(Double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }
    public List<Muestra> getMuestras() {
        return muestras;
    }
    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }
    
    public void agregarMuestra(Muestra muestra){
        this.muestras.add(muestra);
        muestra.setBoya(this);
    }
}
