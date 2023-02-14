package uce.edu.ec.devengamiento.models.objects;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OActividad {

    private String descripcion;
    private String detalleDocente;
    private String evidencias;
    private String nombreOtraInstitucion;
    private LocalDate fechaFin;
    private LocalDate fechaInicio;
    private Long tipoInstitucion;
    private Long idCarrera;
    private Long idFacultad;
    private Long idSubTipoActividad;
    private Long idUniversidad;
    private Long idTipoActividad;
    private Long idPlan;

}
