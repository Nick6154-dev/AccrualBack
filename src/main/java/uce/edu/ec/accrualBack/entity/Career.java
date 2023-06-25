package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "carrera")
@Getter
@Setter
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Long idCareer;

    @NotBlank
    @Column(name = "nombre_carrera")
    private String careerName;

    @ManyToOne
    @JoinColumn(name = "id_facultad")
    private Faculty faculty;

}
