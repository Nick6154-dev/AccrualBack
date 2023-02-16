package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "detalle_docente")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetalleDocente {
    @Id
    @Column(name = "id_detalle_docente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "detalle")
    private String detalle;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ActDocente> actividadesDocente;

}