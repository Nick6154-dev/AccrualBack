package uce.edu.ec.accrual.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "subtipo_actividad")
@Getter
@Setter
public class Subtype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subitpo_actividad")
    private Long idActivitySubtype;

    @NotBlank
    @Column(name = "nombre_subtipo_actividad")
    private String nameActivitySubtype;

    @ManyToOne
    @JoinColumn(name = "id_tipo_actividad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Type activityType;

}
