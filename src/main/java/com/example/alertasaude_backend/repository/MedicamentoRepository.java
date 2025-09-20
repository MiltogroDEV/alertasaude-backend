package com.example.alertasaude_backend.repository;

import com.example.alertasaude_backend.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
    
}
