package com.example.alertasaude_backend.service;

import com.example.alertasaude_backend.entity.Medicamento;
import com.example.alertasaude_backend.entity.Usuario;
import com.example.alertasaude_backend.repository.MedicamentoRepository;
import com.example.alertasaude_backend.repository.UsuarioRepository;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AgendamentoService {

    private static final Logger logger = LoggerFactory.getLogger(AgendamentoService.class);

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    // Armazena o último envio de cada medicamento
    private Map<Long, LocalDateTime> ultimosEnvios = new ConcurrentHashMap<>();

    // Executa a cada hora
    @Scheduled(cron = "0 0 * * * *")
    public void verificarMedicamentos() {
        logger.info("Iniciando verificação de medicamentos às {}", LocalDateTime.now());

        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        for (Medicamento medicamento : medicamentos) {
            verificarEnviarLembrete(medicamento);
        }
    }

    private void verificarEnviarLembrete(Medicamento medicamento) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime ultimoEnvio = ultimosEnvios.get(medicamento.getId());

        // Se nunca enviou ou passou o tempo de tomar
        if (ultimoEnvio == null ||
                agora.isAfter(ultimoEnvio.plusHours(medicamento.getTomarDe()))) {

            try {
                Usuario usuario = usuarioRepository.findById(medicamento.getUsuario().getId())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                emailService.enviarLembreteMedicamento(usuario, medicamento);
                ultimosEnvios.put((long) medicamento.getId(), agora);

                logger.info("Email enviado para {} sobre medicamento {}",
                        usuario.getEmail(), medicamento.getNome());

            } catch (MessagingException e) {
                logger.error("Erro ao enviar email: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("Erro ao processar medicamento: {}", e.getMessage());
            }
        }
    }

    // Método manual para testar
    public void enviarLembreteManual(Integer medicamentoId) throws Exception {
        Medicamento medicamento = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado"));

        Usuario usuario = usuarioRepository.findById(medicamento.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        emailService.enviarLembreteMedicamento(usuario, medicamento);
        ultimosEnvios.put((long) medicamento.getId(), LocalDateTime.now());
    }
}