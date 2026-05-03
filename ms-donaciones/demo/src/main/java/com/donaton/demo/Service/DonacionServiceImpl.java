package com.donaton.demo.Service;

import com.donaton.demo.Repository.CentroAcopioRepository;
import com.donaton.demo.Repository.DonacionRepository;

import org.springframework.stereotype.Service;

@Service
public class DonacionServiceImpl implements DonacionService{
    private final DonacionRepository donacionRepository;
    private final CentroAcopioRepository centroRepository;

    public DonacionServiceImpl(DonacionRepository donacionRepository, CentroAcopioRepository centroRepository) {
        this.donacionRepository = donacionRepository;
        this.centroRepository = centroRepository;
    }


}
