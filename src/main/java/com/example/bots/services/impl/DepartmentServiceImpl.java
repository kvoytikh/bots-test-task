package com.example.bots.services.impl;

import com.example.bots.model.Degree;
import com.example.bots.model.Department;
import com.example.bots.model.Lector;
import com.example.bots.repo.DepartmentRepository;
import com.example.bots.repo.LectorRepository;
import com.example.bots.services.DepartmentService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, LectorRepository lectorRepository) {
        this.departmentRepository = departmentRepository;
        this.lectorRepository = lectorRepository;
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Map<Degree, Long> getStatistics(Long departmentId) {
        Long assistantCount = departmentRepository.countLectorsByDegree(departmentId, Degree.ASSISTANT);
        Long associateProfessorCount = departmentRepository.countLectorsByDegree(departmentId, Degree.ASSOCIATE_PROFESSOR);
        Long professorCount = departmentRepository.countLectorsByDegree(departmentId, Degree.PROFESSOR);

        Map<Degree, Long> statistics = new HashMap<>();
        statistics.put(Degree.ASSISTANT, assistantCount);
        statistics.put(Degree.ASSOCIATE_PROFESSOR, associateProfessorCount);
        statistics.put(Degree.PROFESSOR, professorCount);

        return statistics;
    }

    @Override
    public void assignLectorToDepartment(Long lectorId, Long departmentId) {
        Lector lector = lectorRepository.findById(lectorId).orElseThrow(() -> new RuntimeException("Lector not found"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));

        lector.getDepartments().add(department);
        lectorRepository.save(lector);
    }

    @Override
    public void removeLectorFromDepartment(Long lectorId, Long departmentId) {
        Lector lector = lectorRepository.findById(lectorId).orElseThrow(() -> new RuntimeException("Lector not found"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));

        lector.getDepartments().remove(department);
        lectorRepository.save(lector);
    }
}





