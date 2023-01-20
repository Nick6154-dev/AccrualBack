package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_estructura_i_d")
public class ActEstructuraID {
    @Id
    @Column(name = "id_act_estructura", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_act_estructura")
    private String tipoActEstructura;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesEstructura")
    private List<TipoActividad> tiposActividad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoActEstructura() {
        return tipoActEstructura;
    }

    public void setTipoActEstructura(String tipoActEstructura) {
        this.tipoActEstructura = tipoActEstructura;
    }

}