package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "facultad")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Facultad {
    @Id
    @Column(name = "id_facultad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_facultad")
    private String nombreFacultad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_universidad")
    private Universidad idUniversidad;

}