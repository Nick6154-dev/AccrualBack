package uce.edu.ec.devengamiento.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "universidad")
@Getter
@Setter
public class Universidad {
    @Id
    @Column(name = "id_universidad", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_universidad")
    private String nombreUniversidad;

}