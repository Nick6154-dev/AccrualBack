package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_docente")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ActDocente {
    @Id
    @Column(name = "id_act_docente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_act_docente")
    private String tipoActDocente;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesDocentes")
    @JsonBackReference
    private List<TipoActividad> tiposActividad;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesDocente")
    private List<DetalleDocente> detallesDocente;

}