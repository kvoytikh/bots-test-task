package com.example.bots.services;

import com.example.bots.model.Lector;

import java.util.List;


public interface LectorService {
    List<Lector> searchLectors(String query);
    void promoteLector(Long lectorId);
}


