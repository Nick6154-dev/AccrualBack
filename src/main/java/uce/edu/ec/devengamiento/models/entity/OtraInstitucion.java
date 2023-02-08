package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "otra_institucion")
public class OtraInstitucion {
    @Id
    @Column(name = "id_otra_institucion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_otra_institucion")
    private String nombreOtraInstitucion;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_institucion")
    private TipoInstitucion idTipoInstitucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreOtraInstitucion() {
        return nombreOtraInstitucion;
    }

    public void setNombreOtraInstitucion(String nombreOtraInstitucion) {
        this.nombreOtraInstitucion = nombreOtraInstitucion;
    }

    public TipoInstitucion getIdTipoInstitucion() {
        return idTipoInstitucion;
    }

    public void setIdTipoInstitucion(TipoInstitucion idTipoInstitucion) {
        this.idTipoInstitucion = idTipoInstitucion;
    }

}