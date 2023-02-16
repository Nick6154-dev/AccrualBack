package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_investigadora")
@Getter
@Setter
public class ActInvestigadora {
    @Id
    @Column(name = "id_act_investigadora", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_act_investigadora")
    private String tipoActInvestigadora;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesInvestigadora")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    private List<TipoActividad> tiposActividad;

}