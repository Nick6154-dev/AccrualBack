package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.*;
import uce.edu.ec.devengamiento.models.service.*;

import java.util.List;

@RestController
@SessionAttributes({"docente", "planDevengamiento"})
@CrossOrigin(origins = "http://localhost:5502")
public class Controller {

    private Docente docente;
    private PlanDevengamiento planDevengamiento;
    private DetalleActividad detalleActividad;
    private ActividadInvestigadora actividadInvestigadora;
    private TipoActividad tipoActividad;
    private EstructurasID estructurasID;
    private Innovacion innovacion;
    private ActividadDocente actividadDocente;

    @Autowired
    private IDocenteService docenteService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioRolService usuarioRolService;

    @Autowired
    private ITipoActividadService tipoActividadService;

    @Autowired
    private IPlanDevengamientoService planDevengamientoService;

    @Autowired
    private IActividadInvestigadoraService actividadInvestigadoraService;

    @Autowired
    private IEstructurasIDService estructurasIDService;

    @Autowired
    private IActividadDocenteService actividadDocenteService;

    @Autowired
    private IInnovacionService innovacionService;

    @Autowired
    private IUniversidadService universidadService;

    @Autowired
    private IFacultadService facultadService;

    @Autowired
    private ICarreraService carreraService;

    /*
    ==================================================================================================================
    ==================================================================================================================
    METODOS GET PARA LISTAR TODA LA TABLA
    ==================================================================================================================
    ==================================================================================================================
     */
    @GetMapping("/usuario/listAll/")
    public List<Usuario> listarUsarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/docente/listAll/")
    public List<Docente> listarDocentes() {
        return docenteService.findAll();
    }

    @GetMapping("/rol/listAll/")
    public List<Rol> listarRolesDisponibles() {
        return rolService.findAll();
    }

    @GetMapping("/tipoActividad/listAll/")
    public List<TipoActividad> listarActividades() {
        return tipoActividadService.findAll();
    }

    @GetMapping("/actividadInvestigadora/listAll/")
    public List<ActividadInvestigadora> listarActividadInvestigadora() {
        return actividadInvestigadoraService.findAll();
    }

    @GetMapping("/estructurasID/listAll/")
    public List<EstructurasID> listarEstructurasID() {
        return estructurasIDService.findAll();
    }

    @GetMapping("/actividadDocente/listAll/")
    public List<ActividadDocente> listarActividadDocente() {
        return actividadDocenteService.findAll();
    }

    @GetMapping("/innovacion/listAll/")
    public List<Innovacion> listarInnovacion() {
        return innovacionService.findAll();
    }

    @GetMapping("/universidad/listAll/")
    public List<Universidad> listarUniversidad() {
        return universidadService.findAll();
    }

    @GetMapping("/facultad/listAll/")
    public List<Facultad> listarFacultad() {
        return facultadService.findAll();
    }

    @GetMapping("/carrera/listAll/")
    public List<Carrera> listarCarrera() {
        return carreraService.findAll();
    }

    /*
    ==================================================================================================================
    ==================================================================================================================
    METODOS GET PARA LISTAR FILTRANDO POR PARAMETROS
    ==================================================================================================================
    ==================================================================================================================
     */
    @GetMapping("/carrera/listByF/")
    public List<Carrera> listarCarreraByFacultad(@RequestBody Facultad facultad) {
        return carreraService.findByFacultad(facultad.getId());
    }

    @GetMapping("/facultad/listByU/")
    public List<Facultad> listarFacultadByUniversidad(@RequestBody Universidad universidad) {
        return facultadService.findByIdUniversidad(universidad.getId());
    }

    /*
    ==================================================================================================================
    ==================================================================================================================
    METODOS POST PARA AGREGAR DIFERENTES TABLAS
    ==================================================================================================================
    ==================================================================================================================
     */
    @PostMapping("/docente/add/")
    public void guardarDocente(@RequestBody Docente docente) {
        Usuario usuario = new Usuario(docente.getCedula(), docente.getCedula());
        usuarioService.save(usuario);
        this.docente.setIdUsuario(usuario);
        docenteService.save(this.docente);
    }

    @PostMapping("/plan/add/")
    public void guardarPlan(@RequestBody PlanDevengamiento planDevengamiento) {
        this.planDevengamiento = planDevengamiento;
        this.planDevengamiento.setIdDocente(docente);
        planDevengamientoService.save(planDevengamiento);
    }

    @PostMapping("/tipoActividad/add/")
    public void guardarTipoActividad(@RequestBody TipoActividad tipoActiviad) {
        this.tipoActividad = tipoActiviad;
    }

    @PostMapping("/actividadDevengamiento/add/")
    public void guardarActividadDevengamiento(@RequestBody ActividadDevengamiento actividadDevengamiento) {

    }

    /*
    ==================================================================================================================
    ==================================================================================================================
    METODOS PUT PARA ACTUALIZAR DIFERENTES TABLAS
    ==================================================================================================================
    ==================================================================================================================
     */

    /*
    ==================================================================================================================
    ==================================================================================================================
    METODOS DELETE PARA AGREGAR DIFERENTES TABLAS
    ==================================================================================================================
    ==================================================================================================================
     */

}
