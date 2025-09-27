package com.example.alertasaude_backend.service;

import com.example.alertasaude_backend.entity.Medicamento;
import com.example.alertasaude_backend.entity.Usuario;
import com.example.alertasaude_backend.repository.MedicamentoRepository;
import com.example.alertasaude_backend.repository.UsuarioRepository;
import com.example.alertasaude_backend.web.dto.MedicamentoCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public Medicamento criarMedicamento(MedicamentoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Medicamento medicamento = new Medicamento();

        medicamento.setNome(dto.getNome());
        medicamento.setDosagem(dto.getDosagem());
        medicamento.setTarja(dto.getTarja());
        medicamento.setQuantidade(dto.getQuantidade());
        medicamento.setTomarDe(dto.getTomarDe());
        medicamento.setUsuario(usuario);

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
