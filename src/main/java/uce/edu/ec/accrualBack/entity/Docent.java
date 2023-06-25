package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "docente")
@Getter
@Setter
public class Docent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Long idDocent;

    @NotBlank
    @Column(name = "facultad")
    private String faculty;

    @NotBlank
    @Column(name = "modalidad")
    private String modality;

    @NotBlank
    @Column(name = "categoria")
    private String category;

    @NotNull
    @Column(name = "id_persona")
    private Long idPerson;

}
