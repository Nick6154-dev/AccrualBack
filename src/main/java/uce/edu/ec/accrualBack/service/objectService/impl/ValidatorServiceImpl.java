package uce.edu.ec.accrualBack.service.objectService.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.ValidatorObject;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.ValidatorService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Autowired
    private UniversityInstitutionService universityInstitutionService;

    @Autowired
    private OtherInstitutionService otherInstitutionService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PersonService personService;

    @Autowired
    private DocentService docentService;

    @Autowired
    private MailService mailService;

    @Autowired
    private StatePlanService statePlanService;

    @Autowired
    private AccrualDataService accrualDataService;

    @Autowired
    private NetworkService networkService;

    @Override
    public List<ValidatorObject> findAllPersonDocentPlan() {
        List<ValidatorObject> validatorObjects = new ArrayList<>();
        List<Person> people = personService.findAll();
        ValidatorObject validatorObject;
        for (Person p : people) {
            Optional<Docent> docent = Optional.of(docentService.findByIdPerson(p.getIdPerson()));
            if (docent.get().getIdDocent() == null || docent.get().getIdDocent() == 0) continue;
            validatorObject = new ValidatorObject();
            validatorObject.setPerson(p);
            validatorObject.setDocent(docent.get());
            Optional<List<Plan>> plans = Optional.of(planService.findByDocent(validatorObject.getDocent()));
            if (plans.get().isEmpty()) continue;
            validatorObject.setPlans(plans.get());
            int count = plans.get().size();
            for (Plan plan : plans.get()) {
                if (activityPlanService.existsByIdPlan(plan.getIdPlan())) count--;
            }
            if (count == 0) {
                validatorObject.setObservation("No existe novedad");
            } else {
                validatorObject.setObservation("Algun plan del docente no tiene registrado sus respectivas actividades");
            }
            validatorObjects.add(validatorObject);
        }
        return validatorObjects;
    }

    @Override
    public List<Object> findPlansByPerson(Long idPerson) {
        List<Object> result = new ArrayList<>();
        Docent docent = docentService.findByIdPerson(idPerson);
        List<Plan> plans = planService.findByDocent(docent);
        for (Plan plan : plans) {
            Map<String, Object> aux = new LinkedHashMap<>();
            aux.put("plan", plan);
            StatePlan statePlan = statePlanService.findByIdPlan(plan.getIdPlan());
            if (statePlan.getIdStatePlan() == null) {
                aux.put("statePlan", "Sin revisar");
            } else {
                if (statePlan.getStatePeriod() == 1) {
                    if (statePlan.getStatePlan() == 0) {
                        aux.put("statePlan", "Negado etapa completa");
                    }
                }
                if (statePlan.getStatePeriod() == 2) {
                    if (statePlan.getStatePlan() == 1) {
                        aux.put("statePlan", "Aprobado etapa registro");
                    } else {
                        aux.put("statePlan", "Negado etapa registro");
                    }
                }
                if (statePlan.getStatePeriod() == 3) {
                    if (statePlan.getStatePlan() == 0) {
                        aux.put("statePlan", "Negado etapa validacion");
                    }
                }
            }
            if (plan.getState()) {
                aux.put("statePlan", "Aprobado");
            }
            result.add(aux);
        }
        return result;
    }

    @Override
    public Map<Integer, String> validatePlanByPerson(Map<String, String> newValues) {
        Map<Integer, String> response = new HashMap<>();
        if (newValues.isEmpty()) {
            response.put(400, "El objeto enviado esta vacio");
            return response;
        }
        long idPlan = Long.parseLong(newValues.get("idPlan"));
        if (idPlan == 0) {
            response.put(400, "El id del plan no puede ser 0");
            return response;
        }
        Plan plan = planService.findById(idPlan);
        if (plan.getIdPlan() == null) {
            response.put(400, "No se ha encontrado un plan a ese id especificado");
            return response;
        }
        boolean approved = Boolean.parseBoolean(newValues.get("approved"));
        String observations = newValues.get("observations");
        if (observations.isEmpty()) {
            response.put(400, "Deberia especificar una observacion al plan");
            return response;
        }
        return validatePlan(plan, observations, approved);
    }

    @Override
    public Map<Integer, String> approveAllPlans() {
        Map<Integer, String> response = new HashMap<>();
        List<Plan> plans = planService.findPlansByStateIs(0);
        if (plans.isEmpty()) {
            response.put(400, "No hay planes por aprobar");
            return response;
        }
        for (Plan plan : plans) {
            response.putAll(validatePlan(plan, "Sin observaciones", true));
        }
        response.put(200, "Planes aprobados");
        return response;
    }

    @Override
    public byte[] generateExcelActivitiesPlan(Long idPerson, Long idPlan) {
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent == null) {
            return new byte[0];
        }
        Plan plan = planService.findById(idPlan);
        if (plan == null || !Objects.equals(plan.getIdDocent(), docent.getIdDocent())) {
            return new byte[0];
        }
        Person person = personService.findById(idPerson);
        List<ActivityPlan> activityPlans = activityPlanService.findActivityPlansByIdPlan(plan.getIdPlan());
        //Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        //Create a sheet
        Sheet sheet = workbook.createSheet(person.getName() + " " + person.getLastname());
        //Let's start with the content, first personal information about docent
        personContentExcelInformation(Collections.singletonList(person), sheet, 0);
        //Adding period of this plan
        periodPlanDocentContent(sheet, plan, 3);
        //Now we add activities plan about docent
        if (!activityPlans.isEmpty()) {
            planDocentContentInformation(activityPlans, sheet, 5);
            applyBorderStyle(sheet, createContentStyle(workbook), 6, sheet.getLastRowNum()
                    , 0, sheet.getRow(sheet.getLastRowNum()).getLastCellNum() - 1);
            adjustColumnRow(sheet.getRow(6), sheet);
        }
        //Let's apply some style
        applyBorderStyle(sheet, createHeaderStyle(workbook), 0, 0, 0, 3);
        applyBorderStyle(sheet, createContentStyle(workbook), 1, 1, 0, 3);
        applyBorderStyle(sheet, createHeaderStyle(workbook), 3, 3, 0, 3);
        applyBorderStyle(sheet, createContentStyle(workbook), 4, 4, 0, 3);
        applyBorderStyle(sheet, createHeaderStyle(workbook), 5, 5, 0
                , sheet.getRow(sheet.getLastRowNum()).getLastCellNum() - 1);
        adjustColumnRow(sheet.getRow(0), sheet);
        adjustColumnRow(sheet.getRow(1), sheet);
        adjustColumnRow(sheet.getRow(3), sheet);
        adjustColumnRow(sheet.getRow(4), sheet);
        adjustColumnRow(sheet.getRow(5), sheet);

        //Adding signature
        addSignature(workbook, sheet);

        try {
            //Save the workbook in a bytes array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            //Close the workbook
            workbook.close();
            //Getting the file into bytes
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] generateExcelDocentsInPlan() {
        List<Docent> docents = docentService.findAll();
        List<Person> peopleInPlan = new ArrayList<>();
        List<Plan> plans = planService.findAll();
        for (Plan p : plans) {
            for (Docent d : docents) {
                if (Objects.equals(p.getIdDocent(), d.getIdDocent())) {
                    peopleInPlan.add(personService.findById(d.getIdPerson()));
                }
            }
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Docentes");
        personContentExcelInformation(peopleInPlan, sheet, 0);
        applyBorderStyle(sheet, createHeaderStyle(workbook), 0, 0, 0, 3);
        applyBorderStyle(sheet, createContentStyle(workbook), 1, sheet.getLastRowNum()
                , 0, sheet.getRow(sheet.getLastRowNum()).getLastCellNum() - 1);
        adjustColumnRow(sheet.getRow(0), sheet);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] generateExcelSelectDocentsActivitiesPlan(List<Long> idsPeople) {
        Workbook workbook = new XSSFWorkbook();
        for (Long idPerson : idsPeople) {
            Person person = personService.findById(idPerson);
            String fullName = person.getName() + " " + person.getLastname();
            Sheet sheet = workbook.createSheet(fullName);
            //Adding person information
            personContentExcelInformation(Collections.singletonList(person), sheet, 0);
            Docent docent = docentService.findByIdPerson(person.getIdPerson());
            List<Plan> plans = planService.findByDocent(docent);
            int row = 3;
            for (Plan p : plans) {
                //Adding period of this plan
                periodPlanDocentContent(sheet, p, row);
                //Let's start with the content, first personal information about docent
                row += 2;
                List<ActivityPlan> activityPlans = activityPlanService.findActivityPlansByIdPlan(p.getIdPlan());
                if (!activityPlans.isEmpty()) {
                    planDocentContentInformation(activityPlans, sheet, row);
                    applyBorderStyle(sheet, createContentStyle(workbook), row + 1, sheet.getLastRowNum()
                            , 0, sheet.getRow(sheet.getLastRowNum()).getLastCellNum() - 1);
                    adjustColumnRow(sheet.getRow(row + 1), sheet);
                }
                //Let's apply some style
                applyBorderStyle(sheet, createHeaderStyle(workbook), row - 2, row - 2, 0, 3);
                applyBorderStyle(sheet, createContentStyle(workbook), row - 1, row - 1, 0, 3);
                applyBorderStyle(sheet, createHeaderStyle(workbook), row, row, 0
                        , sheet.getRow(sheet.getLastRowNum()).getLastCellNum() - 1);
                adjustColumnRow(sheet.getRow(row - 2), sheet);
                adjustColumnRow(sheet.getRow(row - 1), sheet);
                adjustColumnRow(sheet.getRow(row), sheet);
                row = sheet.getLastRowNum() + 2;
            }
            adjustColumnRow(sheet.getRow(0), sheet);
            adjustColumnRow(sheet.getRow(1), sheet);
            applyBorderStyle(sheet, createHeaderStyle(workbook), 0, 0, 0, 3);
            applyBorderStyle(sheet, createContentStyle(workbook), 1, 1, 0, 3);
            //Adding signature
            addSignature(workbook, sheet);
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] generateExcelDocentData(Long idPerson) {
        Workbook workbook = new XSSFWorkbook();
        Person person = personService.findById(idPerson);
        String fullName = person.getName() + " " + person.getLastname();
        Docent docent = docentService.findByIdPerson(person.getIdPerson());
        Sheet sheet = workbook.createSheet(fullName);
        //Adding person information
        personContentExcelInformation(Collections.singletonList(person), sheet, 0);
        adjustColumnRow(sheet.getRow(0), sheet);
        adjustColumnRow(sheet.getRow(1), sheet);
        applyBorderStyle(sheet, createHeaderStyle(workbook), 0, 0, 0, 3);
        applyBorderStyle(sheet, createContentStyle(workbook), 1, 1, 0, 3);
        //Adding docent information
        docentContentExcelInformation(docent, sheet, sheet.getLastRowNum() + 2);
        adjustColumnRow(sheet.getRow(sheet.getLastRowNum() - 1), sheet);
        adjustColumnRow(sheet.getRow(sheet.getLastRowNum()), sheet);
        applyBorderStyle(sheet, createHeaderStyle(workbook), sheet.getLastRowNum() - 1, sheet.getLastRowNum() - 1,
                0, 2);
        applyBorderStyle(sheet, createContentStyle(workbook), sheet.getLastRowNum(), sheet.getLastRowNum(),
                0, 2);
        //Adding accrual data information
        accrualDataContentExcelInformation(docent, sheet, sheet.getLastRowNum() + 2);
        adjustColumnRow(sheet.getRow(sheet.getLastRowNum() - 1), sheet);
        adjustColumnRow(sheet.getRow(sheet.getLastRowNum()), sheet);
        applyBorderStyle(sheet, createHeaderStyle(workbook), sheet.getLastRowNum() - 1, sheet.getLastRowNum() - 1,
                0, 4);
        applyBorderStyle(sheet, createContentStyle(workbook), sheet.getLastRowNum(), sheet.getLastRowNum(),
                0, 4);
        //Adding network information
        networkContentExcelInformation(docent, sheet, sheet.getLastRowNum() + 2);
        adjustColumnRow(sheet.getRow(sheet.getLastRowNum() - 1), sheet);
        adjustColumnRow(sheet.getRow(sheet.getLastRowNum()), sheet);
        applyBorderStyle(sheet, createHeaderStyle(workbook), sheet.getLastRowNum() - 1, sheet.getLastRowNum() - 1,
                0, 2);
        applyBorderStyle(sheet, createContentStyle(workbook), sheet.getLastRowNum(), sheet.getLastRowNum(),
                0, 2);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void periodPlanDocentContent(Sheet sheet, Plan plan, int row) {
        Row headerPeriodPlan = sheet.createRow(row);
        headerPeriodPlan.createCell(0).setCellValue("PERIODO");
        sheet.addMergedRegion(new CellRangeAddress(row, row, 0, 3));
        row += 1;
        Row contentPeriodPlan = sheet.createRow(row);
        contentPeriodPlan.createCell(0).setCellValue(plan.getPeriod().getValuePeriod());
        sheet.addMergedRegion(new CellRangeAddress(row, row, 0, 3));
    }

    private void planDocentContentInformation(List<ActivityPlan> activityPlans, Sheet sheet, int row) {
        Row headerActivitiesPlan = sheet.createRow(row);
        headerActivitiesPlan.createCell(0).setCellValue("TIPO DE ACTIVIDAD");
        headerActivitiesPlan.createCell(1).setCellValue("SUBTIPO DE ACTIVIDAD");
        headerActivitiesPlan.createCell(2).setCellValue("DESCRIPCIÓN DE ACTIVIDAD");
        headerActivitiesPlan.createCell(3).setCellValue("FECHA DE INICIO");
        headerActivitiesPlan.createCell(4).setCellValue("FECHA DE FINALIZACIÓN");
        headerActivitiesPlan.createCell(5).setCellValue("EVIDENCIAS");
        headerActivitiesPlan.createCell(6).setCellValue("DESCRIPCIÓN GENERAL");
        headerActivitiesPlan.createCell(7).setCellValue("INSTITUCIÓN");
        headerActivitiesPlan.createCell(8).setCellValue("ENLACE VERIFICACIÓN");
        headerActivitiesPlan.createCell(9).setCellValue("UNIVERSIDAD");
        headerActivitiesPlan.createCell(10).setCellValue("FACULTAD");
        headerActivitiesPlan.createCell(11).setCellValue("CARRERA");
        Row contentActivitiesPlan;
        int numberRow = row + 1;
        int numberCell;
        Description description;
        Institution institution;
        OtherInstitution otherInstitution = new OtherInstitution();
        UniversityInstitution universityInstitution = new UniversityInstitution();
        for (ActivityPlan ap : activityPlans) {
            numberCell = 0;
            description = descriptionService.findDescriptionByActivityPlan(ap);
            institution = institutionService.findInstitutionByActivity(ap.getActivity().getIdActivity());
            if (institution.getIdInstitution() != null) {
                otherInstitution = otherInstitutionService.findOtherInstitutionByInstitution(institution);
                universityInstitution = universityInstitutionService.findUniversityInstitutionByInstitution(institution);
            }
            contentActivitiesPlan = sheet.createRow(numberRow);
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getType().getNameActivityType());
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getSubtype().getNameActivitySubtype());
            numberCell++;
            if (ap.getType().getIdActivityType() == 2) {
                contentActivitiesPlan.createCell(numberCell).setCellValue(description.getDescription());
            } else {
                contentActivitiesPlan.createCell(numberCell).setCellValue("Sin descripción de la actividad");
            }
            numberCell++;
            if (ap.getActivity().getStartDate() != null) {
                contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getStartDate().toString());
            } else {
                contentActivitiesPlan.createCell(numberCell).setCellValue("No ha seleccionado una fecha de comienzo");
            }
            numberCell++;
            if (ap.getActivity().getEndDate() != null) {
                contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getEndDate().toString());
            } else {
                contentActivitiesPlan.createCell(numberCell).setCellValue("No ha seleccionado una fecha de comienzo");
            }
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getEvidences());
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getDescription());
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(institution.getInstitutionName());
            numberCell++;
            if (otherInstitution == null) {
                contentActivitiesPlan.createCell(numberCell).setCellValue("Sin enlace de verificación");
            } else {
                contentActivitiesPlan.createCell(numberCell).setCellValue(otherInstitution.getVerificationLink());
            }
            numberCell++;
            if (universityInstitution.getIdUniversityInstitution() == null) {
                contentActivitiesPlan.createCell(numberCell).setCellValue("No selecciono universidad");
                numberCell++;
                contentActivitiesPlan.createCell(numberCell).setCellValue("No selecciono facultad");
                numberCell++;
                contentActivitiesPlan.createCell(numberCell).setCellValue("No selecciono carrera");
            } else {
                contentActivitiesPlan.createCell(numberCell).setCellValue(universityInstitution.getUniversity().getUniversityName());
                numberCell++;
                contentActivitiesPlan.createCell(numberCell).setCellValue(universityInstitution.getFaculty().getFacultyName());
                numberCell++;
                contentActivitiesPlan.createCell(numberCell).setCellValue(universityInstitution.getCareer().getCareerName());
            }
            numberRow++;
        }
    }

    private void docentContentExcelInformation(Docent docent, Sheet sheet, int row) {
        Row headerDocentInformation = sheet.createRow(row);
        headerDocentInformation.createCell(0).setCellValue("CATEGORIA");
        headerDocentInformation.createCell(1).setCellValue("FACULTAD");
        headerDocentInformation.createCell(2).setCellValue("MODALIDAD");
        row += 1;
        Row contentDocentInformation = sheet.createRow(row);
        contentDocentInformation.createCell(0).setCellValue(docent.getCategory());
        contentDocentInformation.createCell(1).setCellValue(docent.getFaculty());
        contentDocentInformation.createCell(2).setCellValue(docent.getModality());
    }

    private void accrualDataContentExcelInformation(Docent docent, Sheet sheet, int row) {
        AccrualData accrualData = accrualDataService.findByDocent(docent);
        Row headerAccrualDataInformation = sheet.createRow(row);
        if (accrualData.getIdAccrualData() != null) {
            headerAccrualDataInformation.createCell(0).setCellValue("TIEMPO DE DEVENGAMIENTO");
            headerAccrualDataInformation.createCell(1).setCellValue("ENLACE CONTRATO ADENDA");
            headerAccrualDataInformation.createCell(2).setCellValue("FECHA LECTURA TESIS");
            headerAccrualDataInformation.createCell(3).setCellValue("FECHA REINTEGRO");
            headerAccrualDataInformation.createCell(4).setCellValue("ENLACE TESIS");
            row += 1;
            Row contentAccrualDataInformation = sheet.createRow(row);
            contentAccrualDataInformation.createCell(0).setCellValue(accrualData.getAccrualTime());
            contentAccrualDataInformation.createCell(1).setCellValue(accrualData.getContractAddendumLink());
            contentAccrualDataInformation.createCell(2).setCellValue(accrualData.getReadingThesisDate().toString());
            contentAccrualDataInformation.createCell(3).setCellValue(accrualData.getRefundDate().toString());
            contentAccrualDataInformation.createCell(4).setCellValue(accrualData.getThesisLink());
        } else {
            headerAccrualDataInformation.createCell(0).setCellValue("NO TIENE CARGADO LOS DATOS DE DEVENGAMIENTO");
        }
    }

    private void networkContentExcelInformation(Docent docent, Sheet sheet, int row) {
        Network network = networkService.findByDocent(docent);
        Row headerNetworkInformation = sheet.createRow(row);
        if (network.getIdNetworks() != null) {
            headerNetworkInformation.createCell(0).setCellValue("CEDIA");
            headerNetworkInformation.createCell(1).setCellValue("ORCID");
            headerNetworkInformation.createCell(2).setCellValue("RNI SENESCYT");
            row += 1;
            Row contentNetworkInformation = sheet.createRow(row);
            contentNetworkInformation.createCell(0).setCellValue(network.getCedia());
            contentNetworkInformation.createCell(1).setCellValue(network.getOrcidCode());
            contentNetworkInformation.createCell(2).setCellValue(network.getRniSenesyt());
        } else {
            headerNetworkInformation.createCell(0).setCellValue("NO TIENE CARGADO LAS REDES");
        }
    }

    private void addSignature(Workbook workbook, Sheet sheet) {
        CellStyle leftStyle = workbook.createCellStyle();
        leftStyle.setAlignment(HorizontalAlignment.LEFT);
        leftStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        CellStyle centerStyle = workbook.createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        CellStyle bottomBorderStyle = workbook.createCellStyle();
        bottomBorderStyle.setBorderBottom(BorderStyle.THIN);
        int firstRow = sheet.getLastRowNum() + 3;
        int lastRow = sheet.getLastRowNum() + 3;
        int firstCol = 3;
        int lastCol = 4;
        CellRangeAddress cellRangeFrom = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        CellRangeAddress cellRangeSignature1 = new CellRangeAddress(firstRow + 3, lastRow + 3, firstCol, lastCol);
        CellRangeAddress cellRangeSignature2 = new CellRangeAddress(firstRow + 4, lastRow + 4, firstCol, lastCol);
        sheet.addMergedRegion(cellRangeFrom);
        sheet.addMergedRegion(cellRangeSignature1);
        sheet.addMergedRegion(cellRangeSignature2);
        Row row = sheet.createRow(firstRow);
        Cell cell = row.createCell(firstCol);
        cell.setCellValue("REVISADO Y APROBADO POR:");
        cell.setCellStyle(leftStyle);
        Row row2 = sheet.createRow(firstRow + 3);
        Cell cell2 = row2.createCell(firstCol);
        cell2.setCellValue("COORDINADOR UNIDAD DE");
        cell2.setCellStyle(centerStyle);
        Row row3 = sheet.createRow(firstRow + 4);
        Cell cell3 = row3.createCell(firstCol);
        cell3.setCellValue("SEGUIMIENTO DEVENGANTES UCE");
        cell3.setCellStyle(centerStyle);
        for (int col = firstCol; col <= lastCol; col++) {
            CellRangeAddress borderRange = new CellRangeAddress(firstRow + 2, lastRow + 2, col, col);
            RegionUtil.setBorderBottom(BorderStyle.THIN, borderRange, sheet);
        }
    }

    private void personContentExcelInformation(List<Person> people, Sheet sheet, int row) {
        Row headerPersonInformation = sheet.createRow(row);
        headerPersonInformation.createCell(0).setCellValue("NOMBRES");
        headerPersonInformation.createCell(1).setCellValue("APELLIDOS");
        headerPersonInformation.createCell(2).setCellValue("CORREO INSTITUCIONAL");
        headerPersonInformation.createCell(3).setCellValue("CÉDULA/PASAPORTE");
        for (Person person : people) {
            row += 1;
            Row contentPersonInformation = sheet.createRow(row);
            contentPersonInformation.createCell(0).setCellValue(person.getName());
            contentPersonInformation.createCell(1).setCellValue(person.getLastname());
            contentPersonInformation.createCell(2).setCellValue(person.getEmail());
            contentPersonInformation.createCell(3).setCellValue(person.getIdentification());
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    private CellStyle createContentStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    private void applyBorderStyle(Sheet sheet, CellStyle cellStyle, int x1, int x2, int y1, int y2) {
        CellRangeAddress tableRange = new CellRangeAddress(x1, x2, y1, y2);
        for (int rowNum = tableRange.getFirstRow(); rowNum <= tableRange.getLastRow(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            for (int colNum = tableRange.getFirstColumn(); colNum <= tableRange.getLastColumn(); colNum++) {
                Cell cell = row.getCell(colNum);
                if (cell == null) {
                    cell = row.createCell(colNum);
                }
                cell.setCellStyle(cellStyle);
            }
        }
    }

    private void adjustColumnRow(Row row, Sheet sheet) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private Map<Integer, String> validatePlan(Plan plan, String observations, boolean approved) {
        Map<Integer, String> response = new HashMap<>();
        int state;
        if (plan.getPeriod().getState() == 1) {
            statePlanService.deleteByIdPlan(plan.getIdPlan());
            if (approved) {
                plan.setState(true);
                state = 1;
            } else {
                statePlanService.save(new StatePlan(plan.getIdPlan(), plan.getPeriod().getState(), 0));
                state = 0;
            }
        } else if (plan.getPeriod().getState() == 2) {
            statePlanService.deleteByIdPlan(plan.getIdPlan());
            if (approved) {
                statePlanService.save(new StatePlan(plan.getIdPlan(), plan.getPeriod().getState(), 1));
                state = 1;
            } else {
                statePlanService.save(new StatePlan(plan.getIdPlan(), plan.getPeriod().getState(), 0));
                state = 0;
            }
        } else {
            statePlanService.deleteByIdPlan(plan.getIdPlan());
            StatePlan statePlan = statePlanService.findByIdPlan(plan.getIdPlan());
            if (statePlan.getIdStatePlan() == null || statePlan.getStatePlan() == 0) {
                response.put(400, "El plan " + plan.getPeriod().getValuePeriod() + " no fue aprobado en la etapa de registro");
                return response;
            }
            if (approved) {
                plan.setState(true);
                state = 1;
            } else {
                statePlanService.save(new StatePlan(plan.getIdPlan(), plan.getPeriod().getState(), 0));
                state = 0;
            }
        }
        plan.setObservations(observations);
        plan = planService.update(plan, plan.getIdPlan());
        mailService.sendStatePlanNotificationMail(plan.getIdDocent(), state, plan.getObservations(), plan.getPeriod());
        response.put(200, "Plan actualizado su estado correctamente");
        return response;
    }

}
