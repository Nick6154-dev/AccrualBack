package uce.edu.ec.accrualBack.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private List<SocialNetwork> socialNetworks;

}
