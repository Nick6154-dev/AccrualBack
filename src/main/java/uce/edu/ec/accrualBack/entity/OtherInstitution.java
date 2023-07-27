package uce.edu.ec.accrualBack.entity;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_institucion")
    private Institution institution;

    public OtherInstitution() {
    }

    public OtherInstitution(String otherName, String verificationLink, Institution institution) {
        this.otherName = otherName;
        if (verificationLink == null || verificationLink.isEmpty()) {
            this.verificationLink = "Etapa de registro";
        } else {
            this.verificationLink = verificationLink;
        }
        this.institution = institution;
    }
}
