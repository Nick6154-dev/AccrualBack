package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_investigadora")
public class ActInvestigadora {
    @Id
    @Column(name = "id_act_investigadora", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_act_investigadora")
    private String tipoActInvestigadora;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesInvestigadora")
    private List<TipoActividad> tiposActividad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoActInvestigadora() {
        return tipoActInvestigadora;
    }

    public void setTipoActInvestigadora(String tipoActInvestigadora) {
        this.tipoActInvestigadora = tipoActInvestigadora;
    }

}