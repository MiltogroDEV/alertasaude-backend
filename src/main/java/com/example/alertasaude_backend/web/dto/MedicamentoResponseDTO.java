package com.example.alertasaude_backend.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicamentoResponseDTO {

    private int id;
    private String nome;
    private String tomarDe;
}
