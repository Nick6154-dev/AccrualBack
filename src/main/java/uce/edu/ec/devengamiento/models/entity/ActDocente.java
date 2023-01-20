package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_docente")
public class ActDocente {
    @Id
    @Column(name = "id_act_docente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_act_docente")
    private String tipoActDocente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesDocentes")
    private List<TipoActividad> tiposActividad;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesDocente")
    private List<DetalleDocente> detallesDocente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoActDocente() {
        return tipoActDocente;
    }

    public void setTipoActDocente(String tipoActDocente) {
        this.tipoActDocente = tipoActDocente;
    }

    public List<TipoActividad> getTiposActividad() {
        return tiposActividad;
    }

    public void setTiposActividad(List<TipoActividad> tiposActividad) {
        this.tiposActividad = tiposActividad;
    }

    public List<DetalleDocente> getDetallesDocente() {
        return detallesDocente;
    }

    public void setDetallesDocente(List<DetalleDocente> detallesDocente) {
        this.detallesDocente = detallesDocente;
    }
}