package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import java.util.HashMap;
import java.util.Map;

@Service
public class UtilCommonsServiceImpl implements UtilCommonsService {

    @Override
    public ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        result.getFieldErrors().forEach(err -> map
                .put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(map);
    }

}
