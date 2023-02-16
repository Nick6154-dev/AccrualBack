package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.*;
import uce.edu.ec.devengamiento.models.objects.OActividad;
import uce.edu.ec.devengamiento.models.repository.IActividadDevengamientoRepository;
import uce.edu.ec.devengamiento.models.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActividadDevengamientoServiceImpl implements IActividadDevengamietoService {

    @Autowired
    private IActividadDevengamientoRepository repository;
    @Autowired
    private IPlanDevengamientoService service;
    @Autowired
    private ITipoActividadService tipoActividadService;
    @Autowired
    private IActEstructuraIDService estructuraIDService;
    @Autowired
    private IActDocenteService actDocenteService;
    @Autowired
    private IActInvestigadoraService actInvestigadoraService;
    @Autowired
    private IActInnovacionService actInnovacionService;
    @Autowired
    private IDetalleDocenteService detalleDocenteService;
    @Autowired
    private IUniversidadService universidadService;
    @Autowired
    private IFacultadService facultadService;
    @Autowired
    private ICarreraService carreraService;
    @Autowired
    private ITipoInstitucionService tipoInstitucionService;
    @Autowired
    private IOtraInstitucionService otraInstitucionService;
    @Autowired
    private IUniversidadInstitucionService universidadInstitucionService;

    @Override
    public List<ActividadDevengamiento> findAll() {
        return (List<ActividadDevengamiento>) repository.findAll();
    }

    @Override
    public List<ActividadDevengamiento> findAllByIdPlanDevengamiento(Long idPlan) {
        return repository.findActividadDevengamientosByIdPlanDevengamiento(service.findById(idPlan));
    }

    @Override
    public ActividadDevengamiento findById(Long id) {
        return repository.findById(id).orElse(new ActividadDevengamiento());
    }

    @Override
    public ActividadDevengamiento save(OActividad oActividad) {
        Map<String, Object> mapper = loadTipoActividad(oActividad);
        ActividadDevengamiento actividadDevengamiento = repository.save(loadActividad(oActividad));
        tipoActividadService.save((TipoActividad) mapper.get("tipoActividad"));
        if (oActividad.getIdTipoActividad().intValue() == 2) {
            detalleDocenteService.save((DetalleDocente) mapper.get("detalleDocente"));
        }
        TipoInstitucion tipoInstitucion = tipoInstitucionService.save(loadInstitucion(oActividad, actividadDevengamiento));
        if (oActividad.getTipoInstitucion().intValue() == 0) {
            universidadInstitucionService.save(loadUniversidadInstitucion(oActividad, tipoInstitucion));
        } else {
            otraInstitucionService.save(loadOtraInstitucion(oActividad, tipoInstitucion));
        }
        return actividadDevengamiento;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, ActividadDevengamiento actividadDevengamiento) {
        if (repository.existsById(id)) {
            repository.save(actividadDevengamiento);
        }
    }

    private ActividadDevengamiento loadActividad(OActividad oActividad) {
        ActividadDevengamiento actividadDevengamiento = new ActividadDevengamiento();
        List<TipoActividad> tipoActividadList = new ArrayList<>();
        tipoActividadList.add(tipoActividadService.findById(oActividad.getIdTipoActividad()));
        actividadDevengamiento.setDescripcionActividad(oActividad.getDescripcion());
        actividadDevengamiento.setIdPlanDevengamiento(service.findById(oActividad.getIdPlan()));
        actividadDevengamiento.setFechaInicioActividad(oActividad.getFechaInicio());
        actividadDevengamiento.setFechaFinActividad(oActividad.getFechaFin());
        actividadDevengamiento.setEvidenciasLink(oActividad.getEvidencias());
        actividadDevengamiento.setTipoActividades(tipoActividadList);
        return actividadDevengamiento;
    }

    private Map<String, Object> loadTipoActividad(OActividad oActividad) {
        TipoActividad tipoActividad = new TipoActividad();
        DetalleDocente detalleDocente = new DetalleDocente();
        tipoActividad = tipoActividadService.findById(oActividad.getIdTipoActividad());
        switch (oActividad.getIdTipoActividad().intValue()) {
            case 1:
                List<ActEstructuraID> actEstructuraIDList = new ArrayList<>();
                actEstructuraIDList.add(estructuraIDService.findById(oActividad.getIdSubTipoActividad()));
                tipoActividad.setActividadesEstructura(actEstructuraIDList);
                break;
            case 2:
                List<ActDocente> actDocenteList = new ArrayList<>();
                actDocenteList.add(actDocenteService.findById(oActividad.getIdSubTipoActividad()));
                tipoActividad.setActividadesDocentes(actDocenteList);
                detalleDocente.setDetalle(oActividad.getDetalleDocente());
                detalleDocente.setActividadesDocente(actDocenteList);
                break;
            case 3:
                List<ActInvestigadora> actInvestigadoraList = new ArrayList<>();
                actInvestigadoraList.add(actInvestigadoraService.findById(oActividad.getIdSubTipoActividad()));
                tipoActividad.setActividadesInvestigadora(actInvestigadoraList);
                break;
            case 4:
                List<ActInnovacion> actInnovacionList = new ArrayList<>();
                actInnovacionList.add(actInnovacionService.findById(oActividad.getIdSubTipoActividad()));
                tipoActividad.setActividadesInnovacion(actInnovacionList);
                break;
        }
        Map<String, Object> mapper = new HashMap<>();
        mapper.put("tipoActividad", tipoActividad);
        mapper.put("detalleDocente", detalleDocente);
        return mapper;
    }

    private TipoInstitucion loadInstitucion(OActividad oActividad, ActividadDevengamiento actividadDevengamiento) {
        TipoInstitucion tipoInstitucion = new TipoInstitucion();
        if (oActividad.getTipoInstitucion().intValue() == 0) {
            tipoInstitucion.setNombreInstitucion("Universidad Central del Ecuador");
        } else {
            tipoInstitucion.setNombreInstitucion("Otra Institucion");
        }
        tipoInstitucion.setIdActividadDevengamiento(actividadDevengamiento);
        return tipoInstitucion;
    }

    private OtraInstitucion loadOtraInstitucion(OActividad oActividad, TipoInstitucion tipoInstitucion) {
        OtraInstitucion otraInstitucion = new OtraInstitucion();
        otraInstitucion.setNombreOtraInstitucion(oActividad.getNombreOtraInstitucion());
        otraInstitucion.setIdTipoInstitucion(tipoInstitucion);
        return otraInstitucion;
    }

    private UniversidadInstitucion loadUniversidadInstitucion(OActividad oActividad, TipoInstitucion tipoInstitucion) {
        UniversidadInstitucion universidadInstitucion = new UniversidadInstitucion();
        universidadInstitucion.setIdTipoInstitucion(tipoInstitucion);
        universidadInstitucion.setIdUniversidad(universidadService.findById(oActividad.getIdUniversidad()));
        universidadInstitucion.setIdFacultad(facultadService.findById(oActividad.getIdFacultad()));
        universidadInstitucion.setIdCarrera(carreraService.findById(oActividad.getIdCarrera()));
        return universidadInstitucion;
    }

}
