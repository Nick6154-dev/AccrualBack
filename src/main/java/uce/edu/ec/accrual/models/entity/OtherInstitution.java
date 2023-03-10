package uce.edu.ec.accrual.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "otra_institucion")
@Getter
@Setter
public class OtherInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_otra_institucion")
    private Long idOther;

    @NotBlank
    @Column(name = "nombre_otra_institucion")
    private String otherName;

    @NotBlank
    @Column(name = "enlace_verificacion")
    private String verificationLink;

    @OneToOne
    @JoinColumn(name = "id_institucion")
    private Institution institution;

}
