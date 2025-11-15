package com.example.alertasaude_backend.service;

import com.example.alertasaude_backend.entity.Medicamento;
import com.example.alertasaude_backend.entity.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarLembreteMedicamento(Usuario usuario, Medicamento medicamento) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(usuario.getEmail());
        helper.setSubject("⏰ Hora de tomar seu medicamento!");

        String conteudo = criarConteudoEmail(usuario, medicamento);
        helper.setText(conteudo, true);

        mailSender.send(message);
    }

    private String criarConteudoEmail(Usuario usuario, Medicamento medicamento) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background-color: #4CAF50; color: white; padding: 20px; text-align: center; border-radius: 5px 5px 0 0; }
                    .content { background-color: #f9f9f9; padding: 20px; border: 1px solid #ddd; }
                    .medicamento { background-color: white; padding: 15px; margin: 15px 0; border-left: 4px solid #4CAF50; }
                    .footer { text-align: center; padding: 15px; color: #666; font-size: 12px; }
                    .tarja { display: inline-block; padding: 5px 10px; border-radius: 3px; color: white; font-weight: bold; }
                    .tarja-vermelha { background-color: #f44336; }
                    .tarja-preta { background-color: #000000; }
                    .tarja-amarela { background-color: #FFC107; color: #333; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>💊 Alerta de Medicamento</h1>
                    </div>
                    <div class="content">
                        <p>Olá, <strong>%s</strong>!</p>
                        <p>Este é um lembrete para você tomar seu medicamento:</p>
                        
                        <div class="medicamento">
                            <h2>%s</h2>
                            <p><strong>Dosagem:</strong> %s</p>
                            <p><strong>Quantidade:</strong> %d unidade(s)</p>
                            <p><strong>Frequência:</strong> A cada %d hora(s)</p>
                            <p><span class="tarja %s">%s</span></p>
                        </div>
                        
                        <p><strong>⚠️ Lembre-se:</strong></p>
                        <ul>
                            <li>Tome com água</li>
                            <li>Siga as orientações médicas</li>
                            <li>Não interrompa o tratamento sem consultar seu médico</li>
                        </ul>
                    </div>
                    <div class="footer">
                        <p>Este é um email automático. Não responda.</p>
                        <p>Alerta Saúde - Cuidando de você 💚</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(
                usuario.getNome(),
                medicamento.getNome(),
                medicamento.getDosagem(),
                medicamento.getQuantidade(),
                medicamento.getTomarDe(),
                getTarjaClass(medicamento.getTarja()),
                medicamento.getTarja().toUpperCase()
        );
    }

    private String getTarjaClass(String tarja) {
        return switch (tarja.toLowerCase()) {
            case "vermelha" -> "tarja-vermelha";
            case "preta" -> "tarja-preta";
            case "amarela" -> "tarja-amarela";
            default -> "";
        };
    }
}