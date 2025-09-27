package com.example.alertasaude_backend.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicamentoCreateDTO {

    @NotBlank(message = "O nome do medicamento é obrigatório")
    private String nome;

    @NotBlank(message = "A dosagem é obrigatória")
    private String dosagem;

    @NotBlank(message = "A tarja é obrigatória")
    private String tarja;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser maior que 0")
    private Integer quantidade;

    @NotNull(message = "O campo 'tomarDe' é obrigatório")
    @Min(value = 1, message = "O campo 'tomarDe' deve ser maior que 0")
    private Integer tomarDe;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Integer usuarioId;
}
