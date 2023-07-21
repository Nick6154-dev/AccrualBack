package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document(collection = "periodo_docente")
public class PeriodDocent {

    @Id
    private String idPeriodDocent;

    private Long idPeriod;

    private Long idDocent;

    private Integer state;

}
