package uce.edu.ec.accrual.models.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface UtilCommonsService {

    ResponseEntity<Map<String, String>> validate(BindingResult result);

}
