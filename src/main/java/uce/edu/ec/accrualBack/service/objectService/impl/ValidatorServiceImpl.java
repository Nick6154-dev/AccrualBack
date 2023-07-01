package uce.edu.ec.accrualBack.service.objectService.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.ValidatorObject;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UniversityInstitutionService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.ValidatorService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public List<ValidatorObject> findAllPersonDocentPlan() {
        List<ValidatorObject> validatorObjects = new ArrayList<>();
        List<Person> people = personService.findAll();
        ValidatorObject validatorObject;
        for (Person p : people) {
            validatorObject = new ValidatorObject();
            validatorObject.setPerson(p);
            Optional<Docent> docent = Optional.of(docentService.findByIdPerson(p.getIdPerson()));
            if (docent.get().getIdDocent() == null || docent.get().getIdDocent() == 0) continue;
            validatorObject.setDocent(docent.get());
            Optional<List<Plan>> plans = Optional.of(planService.findByDocent(validatorObject.getDocent()));
            if (plans.get().isEmpty()) continue;
            validatorObject.setPlans(plans.get());
            validatorObjects.add(validatorObject);
        }
        return validatorObjects;
    }

    @Override
    public ValidatorObject findPlansByPerson(Long idPerson) {
        ValidatorObject validatorObject = new ValidatorObject();
        Optional<Docent> docent = Optional.ofNullable(docentService.findByIdPerson(idPerson));
        docent.flatMap(value -> Optional.ofNullable(planService.findByDocent(value))).ifPresent(validatorObject::setPlans);
        return validatorObject;
    }

    @Override
    public String validatePlanByPerson(Plan plan) {
        return Optional.of(planService.findById(plan.getIdPlan())).map(value -> {
            value.setState(plan.getState());
            value.setObservations(plan.getObservations());
            planService.save(value);
            mailService.sendStatePlanNotificationMail(plan.getIdDocent(), Long.valueOf(plan.getState()), plan.getObservations(),
                    plan.getPeriod().getValuePeriod());
            return "Estado de plan actualizado junto con las observaciones";
        }).orElseGet(() -> "No se encontro el id del plan para la actualizacion");
    }

    @Override
    public void approveAllPlans() {
        List<Plan> plans = planService.findPlansByStateIs(0);
        for (Plan p : plans) {
            p.setState(1);
            planService.save(p);
            mailService.sendStatePlanNotificationMail(p.getIdDocent(), Long.valueOf(p.getState()), "Sin observaciones",
                    p.getPeriod().getValuePeriod());
        }
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
        //Now we add activities plan about docent
        docentContentExcelInformation(person, sheet);
        //Let's start with the content, first personal information about docent
        planDocentContentInformation(activityPlans, sheet);
        //Let's apply some style
        applyBorderStyle(sheet, createBorderStyle(workbook), 0, 1, 0, 3);
        applyBorderStyle(sheet, createBorderStyle(workbook), 3, sheet.getLastRowNum()
                , 0, sheet.getRow(sheet.getLastRowNum()).getLastCellNum() - 1);
        adjustColumnRow(sheet.getRow(0), sheet);
        adjustColumnRow(sheet.getRow(3), sheet);
        applyBoldStyleRow(sheet.getRow(0), createBoldStyle(workbook));
        applyBoldStyleRow(sheet.getRow(3), createBoldStyle(workbook));
        applyForegroundColorRow(sheet.getRow(0), createForegroundColorRow(workbook));
        applyForegroundColorRow(sheet.getRow(3), createForegroundColorRow(workbook));
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
        List<Docent> docentsInPlan = new ArrayList<>();
        List<Plan> plans = planService.findAll();
        for (Plan p : plans) {
            for (Docent d : docents) {
                if (Objects.equals(p.getIdDocent(), d.getIdDocent())) {
                    docentsInPlan.add(d);
                }
            }
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Docentes");
        Row headerDocentInformation = sheet.createRow(0);
        headerDocentInformation.createCell(0).setCellValue("NOMBRES");
        headerDocentInformation.createCell(1).setCellValue("APELLIDOS");
        headerDocentInformation.createCell(2).setCellValue("CORREO INSTITUCIONAL");
        headerDocentInformation.createCell(3).setCellValue("CEDULA/PASAPORTE");
        int row = 1;
        for (Docent d : docentsInPlan) {
            docentsInPlanContentExcelInformation(personService.findById(d.getIdPerson()), sheet, row);
            row++;
        }
        applyBorderStyle(sheet, createBorderStyle(workbook), 0, sheet.getLastRowNum()
                , 0, sheet.getRow(sheet.getLastRowNum()).getLastCellNum() - 1);
        adjustColumnRow(sheet.getRow(0), sheet);
        applyBoldStyleRow(sheet.getRow(0), createBoldStyle(workbook));
        applyForegroundColorRow(sheet.getRow(0), createForegroundColorRow(workbook));
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void planDocentContentInformation(List<ActivityPlan> activityPlans, Sheet sheet) {
        Row headerActivitiesPlan = sheet.createRow(3);
        headerActivitiesPlan.createCell(0).setCellValue("TIPO DE ACTIVIDAD");
        headerActivitiesPlan.createCell(1).setCellValue("SUBTIPO DE ACTIVIDAD");
        headerActivitiesPlan.createCell(2).setCellValue("DESCRIPCION DE ACTIVIDAD");
        headerActivitiesPlan.createCell(3).setCellValue("FECHA DE INICIO");
        headerActivitiesPlan.createCell(4).setCellValue("FECHA DE FINALIZACION");
        headerActivitiesPlan.createCell(5).setCellValue("EVIDENCIAS");
        headerActivitiesPlan.createCell(6).setCellValue("DESCRIPCION GENERAL");
        headerActivitiesPlan.createCell(7).setCellValue("INSTITUCION");
        headerActivitiesPlan.createCell(8).setCellValue("ENLACE VERIFICACION");
        headerActivitiesPlan.createCell(9).setCellValue("UNIVERSIDAD");
        headerActivitiesPlan.createCell(10).setCellValue("FACULTAD");
        headerActivitiesPlan.createCell(11).setCellValue("CARRERA");
        Row contentActivitiesPlan;
        int numberRow = 4;
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
                contentActivitiesPlan.createCell(numberCell).setCellValue("Sin descripcion de la actividad");
            }
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getStartDate().toString());
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getEndDate().toString());
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getEvidences());
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(ap.getActivity().getDescription());
            numberCell++;
            contentActivitiesPlan.createCell(numberCell).setCellValue(institution.getInstitutionName());
            numberCell++;
            if (otherInstitution == null) {
                contentActivitiesPlan.createCell(numberCell).setCellValue("Sin enlace de verificacion");
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

    private void docentContentExcelInformation(Person person, Sheet sheet) {
        Row headerDocentInformation = sheet.createRow(0);
        headerDocentInformation.createCell(0).setCellValue("NOMBRES");
        headerDocentInformation.createCell(1).setCellValue("APELLIDOS");
        headerDocentInformation.createCell(2).setCellValue("CORREO INSTITUCIONAL");
        headerDocentInformation.createCell(3).setCellValue("CEDULA/PASAPORTE");
        Row contentDocentInformation = sheet.createRow(1);
        contentDocentInformation.createCell(0).setCellValue(person.getName());
        contentDocentInformation.createCell(1).setCellValue(person.getLastname());
        contentDocentInformation.createCell(2).setCellValue(person.getEmail());
        contentDocentInformation.createCell(3).setCellValue(person.getIdentification());
    }

    private void docentsInPlanContentExcelInformation(Person person, Sheet sheet, int row) {
        Row contentDocentInformation = sheet.createRow(row);
        contentDocentInformation.createCell(0).setCellValue(person.getName());
        contentDocentInformation.createCell(1).setCellValue(person.getLastname());
        contentDocentInformation.createCell(2).setCellValue(person.getEmail());
        contentDocentInformation.createCell(3).setCellValue(person.getIdentification());
    }

    private CellStyle createBoldStyle(Workbook workbook) {
        CellStyle boldCellStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldCellStyle.setFont(boldFont);
        return boldCellStyle;
    }

    private CellStyle createBorderStyle(Workbook workbook) {
        CellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        return borderStyle;
    }

    private CellStyle createForegroundColorRow(Workbook workbook) {
        CellStyle foregroundCellStyle = workbook.createCellStyle();
        foregroundCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        foregroundCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return foregroundCellStyle;
    }

    private void applyForegroundColorRow(Row row, CellStyle cellStyle) {
        for (Cell cell : row) {
            cell.setCellStyle(cellStyle);
        }
    }

    private void applyBorderStyle(Sheet sheet, CellStyle borderStyle, int x1, int x2, int y1, int y2) {
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
                cell.setCellStyle(borderStyle);
            }
        }
    }

    private void applyBoldStyleRow(Row row, CellStyle cellStyle) {
        for (Cell cell : row) {
            cell.setCellStyle(cellStyle);
        }
    }

    private void adjustColumnRow(Row row, Sheet sheet) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

}
