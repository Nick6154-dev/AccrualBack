package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_INSTITUCION_UNIVERSIDAD")
public class TipoInstitucionUniversidad {
    @Id
    @Column(name = "ID_TIPO_INS_UNI", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_INSTITUCION", nullable = false)
    private TipoInstitucion idTipoInstitucion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_UNIVERSIDAD", nullable = false)
    private Universidad idUniversidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FACULTAD", nullable = false)
    private Facultad idFacultad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CARRERA", nullable = false)
    private Carrera idCarrera;

    public TipoInstitucionUniversidad() {
    }

    public TipoInstitucionUniversidad(TipoInstitucion idTipoInstitucion, Universidad idUniversidad, Facultad idFacultad, Carrera idCarrera) {
        this.idTipoInstitucion = idTipoInstitucion;
        this.idUniversidad = idUniversidad;
        this.idFacultad = idFacultad;
        this.idCarrera = idCarrera;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoInstitucion getIdTipoInstitucion() {
        return idTipoInstitucion;
    }

    public void setIdTipoInstitucion(TipoInstitucion idTipoInstitucion) {
        this.idTipoInstitucion = idTipoInstitucion;
    }

    public Universidad getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Universidad idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Facultad getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Facultad idFacultad) {
        this.idFacultad = idFacultad;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

}