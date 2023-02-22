package uce.edu.ec.accrual.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "red_social")
@Getter
@Setter
public class SocialNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_red_social")
    private Long idSocialNetwork;

    @NotBlank
    @Column(name = "nombre_red_social")
    private String socialNetworkName;

    @NotBlank
    @Column(name = "enlace_red_social")
    private String socialNetworkLink;

    @ManyToOne
    @JoinColumn(name = "id_redes")
    private Network network;

}
