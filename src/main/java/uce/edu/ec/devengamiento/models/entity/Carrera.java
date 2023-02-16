package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "carrera")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrera {
    @Id
    @Column(name = "id_carrera", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_carrera")
    private String nombreCarrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    private Facultad idFacultad;

}