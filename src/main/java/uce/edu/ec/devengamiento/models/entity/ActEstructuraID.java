package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_estructura_i_d")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ActEstructuraID {
    @Id
    @Column(name = "id_act_estructura", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_act_estructura")
    private String tipoActEstructura;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actividadesEstructura")
    @JsonBackReference
    private List<TipoActividad> tiposActividad;

}