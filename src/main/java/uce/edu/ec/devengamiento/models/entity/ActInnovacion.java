package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_innovacion")
@Getter
@Setter
public class ActInnovacion {
    @Id
    @Column(name = "id_act_innovacion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_act_innovacion")
    private String tipoActInnovacion;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesInnovacion")
    private List<TipoActividad> tiposActividad;

}