package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_actividad")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoActividad {
    @Id
    @Column(name = "id_tipo_actividad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_tipo_actividad")
    private String nombreTipoActividad;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tipoActividades")
    @JsonBackReference
    private List<ActividadDevengamiento> actividadDevengamientos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActDocente> actividadesDocentes;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActEstructuraID> actividadesEstructura;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActInnovacion> actividadesInnovacion;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActInvestigadora> actividadesInvestigadora;

}