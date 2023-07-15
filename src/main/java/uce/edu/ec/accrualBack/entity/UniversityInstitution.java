package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "universidad_institucion")
@Getter
@Setter
public class UniversityInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_universidad_institucion")
    private Long idUniversityInstitution;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_institucion")
    private Institution institution;

    @OneToOne
    @JoinColumn(name = "id_universidad")
    private University university;

    @OneToOne
    @JoinColumn(name = "id_facultad")
    private Faculty faculty;

    @OneToOne
    @JoinColumn(name = "id_carrera")
    private Career career;

}
