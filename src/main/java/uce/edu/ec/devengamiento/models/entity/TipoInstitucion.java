package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipo_institucion")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoInstitucion {
    @Id
    @Column(name = "id_tipo_institucion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_institucion")
    private String nombreInstitucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad_devengamiento")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ActividadDevengamiento idActividadDevengamiento;

}