package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "actividad_devengamiento")
@Getter
@Setter
public class ActividadDevengamiento {
    @Id
    @Column(name = "id_actividad_devengamiento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio_actividad")
    private LocalDate fechaInicioActividad;

    @Column(name = "fecha_fin_actividad")
    private LocalDate fechaFinActividad;

    @Column(name = "descripcion_actividad")
    private String descripcionActividad;

    @Column(name = "evidencias_link")
    private String evidenciasLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_devengamiento")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PlanDevengamiento idPlanDevengamiento;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<TipoActividad> tipoActividades;

}