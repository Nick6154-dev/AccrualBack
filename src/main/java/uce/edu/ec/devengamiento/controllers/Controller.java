package uce.edu.ec.devengamiento.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.devengamiento.models.entity.*;
import uce.edu.ec.devengamiento.models.service.*;

import java.util.List;

@RestController
@SessionAttributes({"docente", "planDevengamiento", "actividadDevengamiento", "tipoInsitucion"})
@CrossOrigin(origins = "http://127.0.0.1:5502/")
public class Controller {

    private Docente docente;
    private PlanDevengamiento planDevengamiento;
    private ActividadDevengamiento actividadDevengamiento;
    private TipoInstitucion tipoInstitucion;
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

    @Autowired
    private ITipoActividadInveService tipoActividadInveService;

    @Autowired
    private ITipoActividadEstService tipoActividadEstService;

    @Autowired
    private ITipoActividadDocenteService tipoActividadDocenteService;

    @Autowired
    private ITipoActividadInnoService tipoActividadInnoService;

    @Autowired
    private IActividadDevengamientoService actividadDevengamientoService;

    @Autowired
    private IDetalleActividadService detalleActividadService;

    @Autowired
    private IActividadDocenteDetalleService actividadDocenteDetalleService;

    @Autowired
    private ITipoInstitucionService tipoInstitucionService;

    @Autowired
    private IOtraInstitucionService otraInstitucionService;

    @Autowired
    private ITipoInstitucionUniversidadService tipoInstitucionUniversidadService;

    @PostConstruct
    public void init() {
        docente = new Docente();
        planDevengamiento = new PlanDevengamiento();
        actividadDevengamiento = new ActividadDevengamiento();
        tipoInstitucion = new TipoInstitucion();
    }

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
        docente.setIdUsuario(usuario);
        docenteService.save(docente);
        this.docente = docente;
    }

    @PostMapping("/plan/add/")
    public void guardarPlan(@RequestBody PlanDevengamiento planDevengamiento) {
        this.planDevengamiento = planDevengamiento;
        this.planDevengamiento.setIdDocente(this.docente);
        planDevengamientoService.save(this.planDevengamiento);
    }

    @PostMapping("/actividadDevengamiento/add/")
    public void guardarActividadDevengamiento(@RequestBody ActividadDevengamiento actividadDevengamiento,
                                              @RequestBody TipoActividad tipoActividad) {
        actividadDevengamiento.setIdTipoActividad(tipoActividad);
        actividadDevengamiento.setIdPlan(this.planDevengamiento);
        actividadDevengamientoService.save(actividadDevengamiento);
        this.actividadDevengamiento = actividadDevengamiento;
    }

    @PostMapping("/actividadDevengamiento/tipoActividad/investigadora/add/")
    public void guardarTipoActividadInves(@RequestBody TipoActividad tipoActividad,
                                          @RequestBody ActividadInvestigadora actividadInvestigadora) {
        tipoActividadInveService.save(new TipoActividadInve(actividadInvestigadora, tipoActividad));
    }

    @PostMapping("/actividadDevengamiento/tipoActividad/innovacion/add/")
    public void guardarTipoActividadInno(@RequestBody TipoActividad tipoActividad,
                                         @RequestBody Innovacion innovacion) {
        tipoActividadInnoService.save(new TipoActividadInno(innovacion, tipoActividad));
    }

    @PostMapping("/actividadDevengamiento/tipoActividad/estructuras/add/")
    public void guardarTipoActividadEstructurasID(@RequestBody TipoActividad tipoActividad,
                                                  @RequestBody EstructurasID estructurasID) {
        tipoActividadEstService.save(new TipoActividadEst(tipoActividad, estructurasID));
    }

    @PostMapping("/actividadDevengamiento/tipoActividad/actividadDocente/add/")
    public void guardarTipoActividadDocente(@RequestBody TipoActividad tipoActividad,
                                            @RequestBody ActividadDocente actividadDocente,
                                            @RequestBody DetalleActividad detalleActividad) {
        detalleActividadService.save(detalleActividad);
        actividadDocenteDetalleService.save(new ActividadDocenteDetalle(detalleActividad, actividadDocente));
        tipoActividadDocenteService.save(new TipoActividadDocente(tipoActividad, actividadDocente));
    }

    @PostMapping("/tipoInstitucion/add/")
    public void guardarTipoInstitucion(@RequestBody TipoInstitucion tipoInstitucion) {
        tipoInstitucion.setIdActividad(actividadDevengamiento);
        tipoInstitucionService.save(tipoInstitucion);
        this.tipoInstitucion = tipoInstitucion;
    }

    @PostMapping("/otraInstitucion/add/")
    public void guardarOtraInstitucion(@RequestBody OtraInstitucion otraInstitucion) {
        otraInstitucion.setIdTipoInstitucion(this.tipoInstitucion);
        otraInstitucionService.save(otraInstitucion);
    }

    @PostMapping("/tipoInstitucionUniversidad/add/")
    public void guardarTipoInstitucionUniversidad(@RequestBody Universidad universidad,
                                                  @RequestBody Facultad facultad,
                                                  @RequestBody Carrera carrera) {
        tipoInstitucionUniversidadService.save(new TipoInstitucionUniversidad(this.tipoInstitucion, universidad, facultad, carrera));
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
