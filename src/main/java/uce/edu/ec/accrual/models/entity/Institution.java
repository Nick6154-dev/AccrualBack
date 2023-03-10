package uce.edu.ec.accrual.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "institucion")
@Getter
@Setter
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long idInstitution;

    @NotBlank
    @Column(name = "nombre_institucion")
    private String institutionName;

    @NotNull
    @Column(name = "id_actividad")
    private Long idActivity;

}
