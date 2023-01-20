package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "red_social_investigacion")
public class RedSocialInvestigacion {
    @Id
    @Column(name = "id_red_social_investigacion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_red_social")
    private String nombreRedSocial;

    @Column(name = "link_red_social")
    private String linkRedSocial;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_datos_devengamiento")
    private DatosDevengamiento idDatosDevengamiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreRedSocial() {
        return nombreRedSocial;
    }

    public void setNombreRedSocial(String nombreRedSocial) {
        this.nombreRedSocial = nombreRedSocial;
    }

    public String getLinkRedSocial() {
        return linkRedSocial;
    }

    public void setLinkRedSocial(String linkRedSocial) {
        this.linkRedSocial = linkRedSocial;
    }

    public DatosDevengamiento getIdDatosDevengamiento() {
        return idDatosDevengamiento;
    }

    public void setIdDatosDevengamiento(DatosDevengamiento idDatosDevengamiento) {
        this.idDatosDevengamiento = idDatosDevengamiento;
    }

}