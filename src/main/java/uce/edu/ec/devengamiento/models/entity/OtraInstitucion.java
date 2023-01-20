package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OTRA_INSTITUCION")
public class OtraInstitucion {
    @Id
    @Column(name = "ID_OTRA_INSTITUCION", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", length = 256)
    private String nombre;

    @Column(name = "ARCHIVO_VERIFICACION", length = 256)
    private String archivoVerificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_INSTITUCION", nullable = false)
    private TipoInstitucion idTipoInstitucion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivoVerificacion() {
        return archivoVerificacion;
    }

    public void setArchivoVerificacion(String archivoVerificacion) {
        this.archivoVerificacion = archivoVerificacion;
    }

    public TipoInstitucion getIdTipoInstitucion() {
        return idTipoInstitucion;
    }

    public void setIdTipoInstitucion(TipoInstitucion idTipoInstitucion) {
        this.idTipoInstitucion = idTipoInstitucion;
    }

}