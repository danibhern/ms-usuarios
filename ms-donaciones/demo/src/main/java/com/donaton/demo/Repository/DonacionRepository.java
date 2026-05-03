package com.donaton.demo.Repository;

import com.donaton.demo.Model.CategoriaDonacion;
import com.donaton.demo.Model.Donacion;
import com.donaton.demo.Model.EstadoDonacion;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonacionRepository extends JpaRepository<Donacion,Long> {

    List<Donacion> findByCategoria(CategoriaDonacion categoria);
    List<Donacion>findCentroAcopio_nombre(String nombreCentro);
    List<Donacion> findByDonacion(EstadoDonacion estado);
}
