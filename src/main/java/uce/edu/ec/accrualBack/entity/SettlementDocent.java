package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "finiquito_docente")
public class SettlementDocent {

    @Id
    private String idSettlementDocent;

    private Long idDocent;

    public SettlementDocent(Long idDocent) {
        this.idDocent = idDocent;
    }

}
