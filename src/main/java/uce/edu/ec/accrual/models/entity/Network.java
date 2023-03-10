package uce.edu.ec.accrual.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "redes")
@Getter
@Setter
public class Network {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_redes")
    private Long idNetworks;

    @NotBlank
    @Column(name = "codigo_orcid")
    private String orcidCode;

    @NotBlank
    @Column(name = "cedia")
    private String cedia;

    @NotBlank
    @Column(name = "rni_senesyt")
    private String rniSenesyt;

    @OneToOne
    @JoinColumn(name = "id_docent")
    private Docent docent;

    @OneToMany
    @JoinColumn(name = "id_red_social")
    private List<SocialNetwork> socialNetworks;

}
