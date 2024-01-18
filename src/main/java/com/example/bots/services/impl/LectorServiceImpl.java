package com.example.bots.services.impl;

import com.example.bots.handler.LectorPromotionException;
import com.example.bots.model.Lector;
import com.example.bots.repo.LectorRepository;
import com.example.bots.services.LectorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LectorServiceImpl implements LectorService {

    private final LectorRepository lectorRepository;

    public LectorServiceImpl(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Override
    public List<Lector> searchLectors(String query) {
        return lectorRepository.searchLectors(query);
    }

    @Override
    public void promoteLector(Long lectorId) {
        int updatedRows = lectorRepository.promoteLector(lectorId);
        if (updatedRows == 0) {
            throw new LectorPromotionException("Failed to promote lector with ID: " + lectorId);
        }
    }
}

