package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RED_SOCIAL_INVESTIGACION")
public class RedSocialInvestigacion {
    @Id
    @Column(name = "ID_RED_SOCIAL", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_RED_SOCIAL", length = 256)
    private String nombreRedSocial;

    @Column(name = "LINK_RED_SOCIAL", length = 256)
    private String linkRedSocial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}