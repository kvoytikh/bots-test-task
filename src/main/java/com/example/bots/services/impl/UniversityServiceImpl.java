package com.example.bots.services.impl;

import com.example.bots.model.Degree;
import com.example.bots.model.Department;
import com.example.bots.model.Lector;
import com.example.bots.services.DepartmentService;
import com.example.bots.services.LectorService;
import com.example.bots.services.UniversityService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final LectorService lectorService;
    private final DepartmentService departmentService;

    public UniversityServiceImpl(LectorService lectorService, DepartmentService departmentService) {
        this.lectorService = lectorService;
        this.departmentService = departmentService;
    }

    @Override
    public List<Lector> searchLectors(String query) {
        return lectorService.searchLectors(query);
    }

    @Override
    public void promoteLector(Long lectorId) {
        lectorService.promoteLector(lectorId);
    }

    @Override
    public Department findById(Long id) {
        return departmentService.findById(id);
    }

    @Override
    public Map<Degree, Long> getDepartmentStatistics(Long departmentId) {
        return departmentService.getStatistics(departmentId);
    }

    @Override
    public void assignLectorToDepartment(Long lectorId, Long departmentId) {
        departmentService.assignLectorToDepartment(lectorId, departmentId);
    }

    @Override
    public void removeLectorFromDepartment(Long lectorId, Long departmentId) {
        departmentService.removeLectorFromDepartment(lectorId, departmentId);
    }
}

