package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "detalle_docente")
public class DetalleDocente {
    @Id
    @Column(name = "id_detalle_docente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "detalle")
    private String detalle;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ActDocente> actividadesDocente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public List<ActDocente> getActividadesDocente() {
        return actividadesDocente;
    }

    public void setActividadesDocente(List<ActDocente> actividadesDocente) {
        this.actividadesDocente = actividadesDocente;
    }
}