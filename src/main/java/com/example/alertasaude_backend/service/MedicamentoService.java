package com.example.alertasaude_backend.service;

import com.example.alertasaude_backend.entity.Medicamento;
import com.example.alertasaude_backend.entity.Usuario;
import com.example.alertasaude_backend.repository.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public Medicamento criarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public List<Medicamento> listarTodos() {
        return medicamentoRepository.findAll();
    }

    public Medicamento buscarPorId(int id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado"));
    }

    public Medicamento deletarPorId(int id) {
        Medicamento medicamento = buscarPorId(id);
        medicamentoRepository.deleteById(id);
        return medicamento;
    }
}
