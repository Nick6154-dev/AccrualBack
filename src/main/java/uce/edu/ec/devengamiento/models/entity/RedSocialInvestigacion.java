package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "red_social_investigacion")
@Getter
@Setter
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

}