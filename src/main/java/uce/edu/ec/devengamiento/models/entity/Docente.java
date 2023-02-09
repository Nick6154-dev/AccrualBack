package uce.edu.ec.devengamiento.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "docente")
@Getter
@Setter
public class Docente {

    @Id
    @Column(name = "id_docente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "cedula")
    private String cedula;

}