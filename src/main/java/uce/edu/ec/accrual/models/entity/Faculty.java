package uce.edu.ec.accrual.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "facultad")
@Getter
@Setter
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facutad")
    private Long idFaculty;

    @NotBlank
    @Column(name = "nombre_facultad")
    private String facultyName;

    @ManyToOne
    @JoinColumn(name = "id_universidad")
    private University university;

}
