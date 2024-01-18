package com.example.bots.services;

import com.example.bots.model.Degree;
import com.example.bots.model.Department;
import com.example.bots.model.Lector;
import java.util.List;
import java.util.Map;

public interface UniversityService {
    List<Lector> searchLectors(String query);
    void promoteLector(Long lectorId);
    Department findById(Long id);
    Map<Degree, Long> getDepartmentStatistics(Long departmentId);
    void assignLectorToDepartment(Long lectorId, Long departmentId);
    void removeLectorFromDepartment(Long lectorId, Long departmentId);
}
