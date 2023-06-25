package uce.edu.ec.accrualBack.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tipo_actividad")
@Getter
@Setter
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_actividad")
    private Long idActivityType;

    @NotBlank
    @Column(name = "nombre_tipo_actividad")
    private String nameActivityType;

    @OneToMany(mappedBy = "activityType")
    @Column(name = "id_subtipo_actividad")
    @JsonBackReference
    private List<Subtype> subtypes;

}
