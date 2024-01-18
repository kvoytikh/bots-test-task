package com.example.bots.services;

import com.example.bots.model.Degree;
import com.example.bots.model.Department;
import java.util.Map;

public interface DepartmentService {
    Department findById(Long id);
    Map<Degree, Long> getStatistics(Long departmentId);
    void assignLectorToDepartment(Long lectorId, Long departmentId);
    void removeLectorFromDepartment(Long lectorId, Long departmentId);
}




