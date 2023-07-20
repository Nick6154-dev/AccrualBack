package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "periodo")
@Getter
@Setter
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo")
    private Long idPeriod;

    @NotBlank
    @Column(name = "valor_periodo")
    private String valuePeriod;

    @NotNull
    @Column(name = "activo")
    private Boolean active;

    @NotNull
    @Column(name = "estado")
    private Integer state;

}
