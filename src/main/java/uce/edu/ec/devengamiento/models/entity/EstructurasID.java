package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ESTRUCTURAS_I_D")
public class EstructurasID {
    @Id
    @Column(name = "ID_ESTRUCTURA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_ESTRUCTURA", length = 256)
    private String tipoEstructura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoEstructura() {
        return tipoEstructura;
    }

    public void setTipoEstructura(String tipoEstructura) {
        this.tipoEstructura = tipoEstructura;
    }

}