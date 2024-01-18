package com.example.bots.controllers;

import com.example.bots.handler.LectorPromotionException;
import com.example.bots.model.Degree;
import com.example.bots.model.Department;
import com.example.bots.model.Lector;
import com.example.bots.services.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }
    @GetMapping("/search")
    public List<Lector> searchLectors(@RequestParam String query) {
        return universityService.searchLectors(query);
    }

    @PutMapping("/promote/{lectorId}")
    public ResponseEntity<String> promoteLector(@PathVariable Long lectorId) {
        try {
            universityService.promoteLector(lectorId);
            return ResponseEntity.ok("Lector promoted successfully");
        } catch (LectorPromotionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }






    @GetMapping("/department/{departmentId}/statistics")
    public Map<Degree, Long> getDepartmentStatistics(@PathVariable Long departmentId) {
        return universityService.getDepartmentStatistics(departmentId);
    }
    @GetMapping("/department/{departmentId}")
    public Department getDepartment(@PathVariable Long departmentId) {
        return universityService.findById(departmentId);
    }

    @PostMapping("/department/{departmentId}/assign/{lectorId}")
    public void assignLectorToDepartment(@PathVariable Long lectorId, @PathVariable Long departmentId) {
        universityService.assignLectorToDepartment(lectorId, departmentId);
    }

    @PostMapping("/department/{departmentId}/remove/{lectorId}")
    public void removeLectorFromDepartment(@PathVariable Long lectorId, @PathVariable Long departmentId) {
        universityService.removeLectorFromDepartment(lectorId, departmentId);
    }
}


