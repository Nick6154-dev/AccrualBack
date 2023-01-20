package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_actividad")
public class TipoActividad {
    @Id
    @Column(name = "id_tipo_actividad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_tipo_actividad")
    private String nombreTipoActividad;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tipoActividades")
    private List<ActividadDevengamiento> actividadDevengamientos;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActDocente> actividadesDocentes;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActEstructuraID> actividadesEstructura;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActInnovacion> actividadesInnovacion;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActInvestigadora> actividadesInvestigadora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTipoActividad() {
        return nombreTipoActividad;
    }

    public void setNombreTipoActividad(String nombreTipoActividad) {
        this.nombreTipoActividad = nombreTipoActividad;
    }

    public List<ActividadDevengamiento> getActividadDevengamientos() {
        return actividadDevengamientos;
    }

    public void setActividadDevengamientos(List<ActividadDevengamiento> actividadDevengamientos) {
        this.actividadDevengamientos = actividadDevengamientos;
    }

    public List<ActDocente> getActividadesDocentes() {
        return actividadesDocentes;
    }

    public void setActividadesDocentes(List<ActDocente> actividadesDocentes) {
        this.actividadesDocentes = actividadesDocentes;
    }

    public List<ActEstructuraID> getActividadesEstructura() {
        return actividadesEstructura;
    }

    public void setActividadesEstructura(List<ActEstructuraID> actividadesEstructura) {
        this.actividadesEstructura = actividadesEstructura;
    }

    public List<ActInnovacion> getActividadesInnovacion() {
        return actividadesInnovacion;
    }

    public void setActividadesInnovacion(List<ActInnovacion> actividadesInnovacion) {
        this.actividadesInnovacion = actividadesInnovacion;
    }

    public List<ActInvestigadora> getActividadesInvestigadora() {
        return actividadesInvestigadora;
    }

    public void setActividadesInvestigadora(List<ActInvestigadora> actividadesInvestigadora) {
        this.actividadesInvestigadora = actividadesInvestigadora;
    }
}