package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "INNOVACION")
public class Innovacion {
    @Id
    @Column(name = "ID_INNOVACION", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_INNOVACION", length = 256)
    private String tipoInnovacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoInnovacion() {
        return tipoInnovacion;
    }

    public void setTipoInnovacion(String tipoInnovacion) {
        this.tipoInnovacion = tipoInnovacion;
    }

}