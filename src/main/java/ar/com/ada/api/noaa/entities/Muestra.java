package ar.com.ada.api.noaa.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "muestra")
public class Muestra {
    
    @Id
    @Column(name = "muestra_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMuestra;

    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;

    @Column(name = "horario_muestra")
    private Date HorarioMuestra;

    @Column( name = "matricula_embarcacion")
    private String matriculaEmbarcacion;

    private Double longitud;

    private Double latitud;

    @Column( name = "altura_nivel_mar")
    private Double alturaNivelMar;

    public Integer getIdMuestra() {
        return idMuestra;
    }
    public void setIdMuestra(Integer idMuestra) {
        this.idMuestra = idMuestra;
    }
    public Boya getBoya() {
        return boya;
    }
    public void setBoya(Boya boya) {
        this.boya = boya;
    }
    public Date getHorarioMuestra() {
        return HorarioMuestra;
    }
    public void setHorarioMuestra(Date horarioMuestra) {
        HorarioMuestra = horarioMuestra;
    }
    public String getMatriculaEmbarcacion() {
        return matriculaEmbarcacion;
    }
    public void setMatriculaEmbarcacion(String matriculaEmbarcacion) {
        this.matriculaEmbarcacion = matriculaEmbarcacion;
    }
    public Double getLongitud() {
        return longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    public Double getAlturaNivelMar() {
        return alturaNivelMar;
    }
    public void setAlturaNivelMar(Double alturaNivelMar) {
        this.alturaNivelMar = alturaNivelMar;
    }

    
}
