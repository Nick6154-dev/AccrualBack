package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "actividad")
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long idActivity;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "evidencias")
    private String evidences;

    @Column(name = "fecha_inicio")
    private LocalDate startDate;

    @Column(name = "fecha_fin")
    private LocalDate endDate;

    public Activity() {
    }

    public Activity(String description, String evidences, LocalDate startDate, LocalDate endDate) {
        if (evidences == null || evidences.isEmpty()) {
            this.evidences = "Etapa de planificacion";
        } else {
            this.evidences = evidences;
        }
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
