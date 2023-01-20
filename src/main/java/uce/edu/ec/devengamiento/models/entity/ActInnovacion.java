package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_innovacion")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoActInnovacion() {
        return tipoActInnovacion;
    }

    public void setTipoActInnovacion(String tipoActInnovacion) {
        this.tipoActInnovacion = tipoActInnovacion;
    }

}